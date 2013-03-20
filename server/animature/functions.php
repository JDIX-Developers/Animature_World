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
	$domain = explode('@', $email);
	$has_mx = getmxrr($domain[1], $mxhost);
	$is_valid = ($is_valid && $has_mx);

	if ($is_valid)
	{
		$query = db()->query("SELECT COUNT(*) FROM `users` WHERE `email` = '".db()->real_escape_string($email)."';");
		$results = 0;
		foreach ($query->fetch_row() as $number)
		{
			$results = (int) $number[0];
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
		$results = (int) $number[0];
	}

	return $results > 0;
}

function exists_email($email)
{
	$query = db()->query("SELECT COUNT(*) FROM `users` WHERE `email` = '".db()->real_escape_string($email)."'");

	$results = 0;
	foreach ($query->fetch_row() as $number)
	{
		$results = (int) $number[0];
	}

	return $results > 0;
}

function valid_login($email, $pass)
{
	if ( ! preg_match('/[0-9a-f]{40}/', $pass))
	{
		return FALSE;
	}

	$query = db()->query("SELECT COUNT(*) FROM `users` WHERE `email` = '".db()->real_escape_string($email)."' AND `password` = '".db()->real_escape_string($pass)."'");

	$results = 0;
	foreach ($query->fetch_row() as $number)
	{
		$results = (int) $number[0];
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
			return (int) $expiration[0];
		}
	}

	return 0;
}


/* End of file functions.php */
/* Location: ./animature/functions.php */