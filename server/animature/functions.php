<?php defined('IN_API') OR (header('Location: http://jdix.razican.com/404.php', 404) && exit);

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
	$query = db()->query("SELECT COUNT(*) FROM `users` WHERE `username` = '".db()->real_escape_string($user)."'");

	$results = 0;
	foreach ($query->fetch_row() as $number)
	{
		$results = (int) $number;
	}

	return $results > 0;
}

function exists_email($email)
{
	$query = db()->query("SELECT COUNT(*) FROM `users` WHERE `email` = '".db()->real_escape_string($email)."'");

	$results = 0;
	foreach ($query->fetch_row() as $number)
	{
		$results = (int) $number;
	}

	return $results > 0;
}

function expiration($token)
{
	$query = db()->query("SELECT `expiration` FROM `sessions` WHERE `token` = '".db()->real_escape_string($token)."'");

	if ($query->num_rows > 0)
	{
		foreach ($query->fetch_row() as $expiration)
		{
			return $expiration;
		}
	}

	return 0;
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