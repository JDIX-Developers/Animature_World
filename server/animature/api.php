<?php

function is_ssl()
{
	return TRUE;//( ! empty($_SERVER['HTTPS']) && $_SERVER['HTTPS'] != 'off');
}

function is_animature()
{
	return (bool) preg_match('/Animature\/[1-9]+\.[0-9]+\.[0-9]+/', $_SERVER['HTTP_USER_AGENT']);
}

function db()
{
	static $db;

	if (empty($db))
	{
		require('dbconfig.php');

		$db = new mysqli($dbsettings['server'], $dbsettings['user'], $dbsettings['pass'], $dbsettings['name']);
		if ( ! is_null($db->connect_error))
		{
			header('Location: http://jdix.razican.com/404.php', 404);
			exit;
		}

		$db->set_charset('utf8');
		unset($dbsettings);
	}

	return $db;
}

define('IN_API', TRUE);
db()->query("DELETE FROM `sessions` WHERE expiration < ".time().";");

if (is_ssl() && is_animature() && isset($_POST['action']) && ! empty($_POST['action']))
{
	require('functions.php');
	$result = array();

	if (isset($_POST['token']) && preg_match('/[0-9a-f]{32}/', $_POST['token']))
	{
		$token_expiration = token_expiration($_POST['token']);
		if ($token_expiration < 900)
		{
			$result['regenerate'] = TRUE;

			if ($token_expiration === 0)
			{
				echo json_encode($result);
				exit;
			}
		}
	}
	elseif (isset($_POST['token']))
	{
		header('Location: http://jdix.razican.com/404.php', 404);
		exit;
	}

	switch ($_POST['action'])
	{
		case 'login':
			if (isset($_POST['method']) && ($_POST['method'] === 'manual' OR $_POST['method'] === 'auto') &&
				isset($_POST['email']) && ! empty($_POST['email']) && isset($_POST['pass']) && ! empty($_POST['pass']))
			{
				$exists_email = exists_email($_POST['email']);
				$valid_login = valid_login($_POST['email'], $_POST['pass']);
				if ($exists_email && $valid_login)
				{
					$token = md5($_POST['email'].$_POST['pass'].microtime(TRUE)."--Animature");
					db()->query("INSERT INTO `sessions` VALUES ('".$token."', (SELECT `id` FROM `users` WHERE `email` = '".db()->real_escape_string($_POST['email'])."'), ".(time()+7200).");");
				}

				if ($_POST['method'] === 'manual')
				{
					$result['email'] = $exists_email;
					$result['pass'] = $valid_login;
				}
				if (isset($token))
				{
					$result['token'] = $token;
				}
			}
			else
			{
				header('Location: http://jdix.razican.com/404.php', 404);
				exit;
			}
		break;
		case 'register':
			if (isset($_POST['user']) && ! empty($_POST['user']) && isset($_POST['email']) && ! empty($_POST['email']) && isset($_POST['pass']) && preg_match('/[a-f0-9]{40}/', $_POST['pass']))
			{
				$valid_email = valid_email($_POST['email']);
				$exists_user = exists_user($_POST['user']);
				if ($valid_email && ! $exists_user)
				{
					db()->query("INSERT INTO `users` (`email`, `password`, `username`) ".
						"VALUES ('".db()->real_escape_string($_POST['email'])."', '".
						db()->real_escape_string($_POST['pass'])."', '".db()->real_escape_string($_POST['user'])."');");
					$token = md5($_POST['email'].$_POST['pass'].microtime(TRUE)."--Animature");

					db()->query("INSERT INTO `sessions` VALUES ('".$token."', (SELECT `id` FROM `users` WHERE `email` = '".db()->real_escape_string($_POST['email'])."'), ".(time()+7200).");");
				}

				$result['user'] = ! $exists_user;
				$result['email'] = $valid_email;
				if (isset($token))
				{
					$result['token'] = $token;
				}
			}
			else
			{
				header('Location: http://jdix.razican.com/404.php', 404);
				exit;
			}
		break;
		default:
			header('Location: http://jdix.razican.com/404.php', 404);
			exit;
	}
	echo json_encode($result);
}
else
{
	header('Location: http://jdix.razican.com/404.php', 404);
	exit;
}


/* End of file api.php */
/* Location: ./animature/api.php */