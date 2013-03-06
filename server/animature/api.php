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
			if (isset($_POST['user']) && ! empty($_POST['user']) && isset($_POST['pass']) && ! empty($_POST['pass']))
			{
				$result = array(
					'token' => md5('prueba'),
					'user' => TRUE,
					'pass' => TRUE);

				echo json_encode($result);
			}
		break;
		case 'register':
		break;
		case 'test':
			echo json_encode(array('text' => 'Hola'));
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