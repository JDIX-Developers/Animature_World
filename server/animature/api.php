<?php

function is_ssl()
{
	return ( ! empty($_SERVER['HTTPS']) && $_SERVER['HTTPS'] != 'off');
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

			foreach ($v as $key =>$int)
			{
				$v[$key] = (int) $int;
			}
		}
		return $v;
	}
}

//if (is_ssl() && is_animature())
//{





//}