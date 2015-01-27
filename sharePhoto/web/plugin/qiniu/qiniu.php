<?php
define('AccessKey', 'BHp5Ky88xYZ9FR-aLUIeAVT3KtiOhi-Ie0iG1l1H');
define('SecretKey', '7gTIlQHKIum7QAhde-4a1yNIJRiG1rYPloM3sSwe');

/**
 * urlsafe_base64_encode
 *
 * @desc URL安全方式的base64编码
 * @param string $str
 * @return string
 */
function urlsafe_base64_encode($str){
	$find = array('+','/');
	$replace = array('-', '_');
	return str_replace($find, $replace, base64_encode($str));
}
/**
 * 上传授权凭证
 */
function uploadToken(){
	$flags['scope'] = 'showme';
	$encodedFlags = urlsafe_base64_encode(json_encode($flags));
	$signature = hmac_sha1($encodedFlags, SecretKey);
	$encodedSign = urlsafe_base64_encode($signature);
	return AccessKey.':'.$encodedSign.':'.$encodedFlags;
}

function hmac_sha1($key, $data){
    // Adjust key to exactly 64 bytes
    if (strlen($key) > 64) {
        $key = str_pad(sha1($key, true), 64, chr(0));
    }
    if (strlen($key) < 64) {
        $key = str_pad($key, 64, chr(0));
    }

    // Outter and Inner pad
    $opad = str_repeat(chr(0x5C), 64);
    $ipad = str_repeat(chr(0x36), 64);

    // Xor key with opad & ipad
    for ($i = 0; $i < strlen($key); $i++) {
        $opad[$i] = $opad[$i] ^ $key[$i];
        $ipad[$i] = $ipad[$i] ^ $key[$i];
    }

    return sha1($opad.sha1($ipad.$data, true));
}
?>