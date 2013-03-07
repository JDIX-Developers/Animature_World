<?php

function is_ssl()
{
	return TRUE;//( ! empty($_SERVER['HTTPS']) && $_SERVER['HTTPS'] != 'off');
}

function is_animature()
{
	return (bool) preg_match('/Animature\/[1-9]+\.[0-9]+\.[0-9]+/', $_SERVER['HTTP_USER_AGENT']);
}

function cl_version()
{
	if ( ! is_animature())
	{
		return NULL;
	}
	else
	{
		static $v;
		if (empty($v))
		{
			$va = array();
			preg_match('/^Animature\/[1-9]+\.[0-9]+\.[0-9]+$/', $_SERVER['HTTP_USER_AGENT'], $va);
			$v = explode('.', str_replace('Animature/', '', $va[0]));

			foreach ($v as $key => $int)
			{
				$v[$key] = (int) $int;
			}
		}
		return $v;
	}
}

function valid_email($email)
{
	$is_valid = (bool) filter_var($email, FILTER_VALIDATE_EMAIL);
	$has_mx = getmxrr(explode('@', $email), $mxhost);
	$is_valid = ($is_valid && $has_mx[1]);

	if ($is_valid)
	{
		$query = db()->query("SELECT COUNT(*) FROM `users` WHERE `email` = '".db()->real_escape_string($email)."';");
		$results = 0;
		foreach ($query->fetch_row() as $number)
		{
			$results = (int) $number;
		}

		$is_valid = ($results === 0);
	}

	return $is_valid;
}

function exists_user($user)
{
	$query = db()->query("SELECT COUNT(*) FROM `users` WHERE `user` = '".db()->real_escape_string($user)."'");

	$results = 0;
	foreach ($query->fetch_row() as $number)
	{
		$results = (int) $number;
	}

	return $results > 0;
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

if (is_ssl() && is_animature() && isset($_POST['action']) && ! empty($_POST['action']))
{
	define('IN_API', TRUE);

	switch ($_POST['action'])
	{
		case 'login':
			if (isset($_POST['mode']) && ! empty($_POST['mode']) && isset($_POST['email']) && ! empty($_POST['email']) && isset($_POST['pass']) && ! empty($_POST['pass']))
			{
				//TODO
				$result = array(
					'token' => md5('prueba'),
					'email' => TRUE,
					'pass' => TRUE);

				echo json_encode($result);
			}
			else
			{
				header('Location: http://jdix.razican.com/404.php', 404);
				exit;
			}
		break;
		case 'register':
			if (isset($_POST['user']) && ! empty($_POST['user']) && isset($_POST['email']) && ! empty($_POST['email']) && isset($_POST['pass']) && ! empty($_POST['pass']))
			{
				//TODO Falta el registro y la creación de la sesión
				$result = array(
					'token' => md5($_POST['user'].$_POST['pass'].microtime(TRUE)."--Animature"),
					'user' => exists_user($_POST['user']),
					'email' => valid_email($_POST['email']));

				echo json_encode($result);
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
}
else
{
	header('Location: http://jdix.razican.com/404.php', 404);
	exit;
}