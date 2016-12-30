'use strict';

const JWT = require('../../common/utils/secret/jwt').default;
const jwt1024 = new JWT(`${think.ROOT_PATH}/cert/1024/rsa_public_key.pem`,
	`${think.ROOT_PATH}/cert/1024/rsa_pkcs8_private_key.pem`);

const jwt2048 = new JWT(`${think.ROOT_PATH}/cert/2048/rsa_public_key.pem`,
	`${think.ROOT_PATH}/cert/2048/rsa_pkcs8_private_key.pem`);

const jwt4096 = new JWT(`${think.ROOT_PATH}/cert/4096/rsa_public_key.pem`,
	`${think.ROOT_PATH}/cert/4096/rsa_pkcs8_private_key.pem`);

const jwt8192 = new JWT(`${think.ROOT_PATH}/cert/8192/rsa_public_key.pem`,
	`${think.ROOT_PATH}/cert/8192/rsa_pkcs8_private_key.pem`);

const str = '你好吗？';
const max = 1000;


export default class extends think.controller.base {
	async indexAction() {
		await this.encrypt1024();
		await this.encrypt2048();
		await this.encrypt4096();
		await this.encrypt8192();
		return this.json({a: 1});
	}

	async encrypt1024() {
		let t = Date.now();
		for (let i = 0; i < max; i++) {
			let encrypt1024 = await jwt1024.encrypt(str);
			let decrypt1024 = await jwt1024.decrypt(encrypt1024);
		}
		console.log('decrypt1024: ' + (Date.now() - t) + ' ms');
	}

	async encrypt2048() {
		let t = Date.now();
		for (let i = 0; i < max; i++) {
			let encrypt2048 = await jwt2048.encrypt(str);
			let decrypt2048 = await jwt2048.decrypt(encrypt2048);
		}
		console.log('decrypt2048: ' + (Date.now() - t) + ' ms');
	}

	async encrypt4096() {
		let t = Date.now();
		for (let i = 0; i < max; i++) {
			let encrypt4096 = await jwt4096.encrypt(str);
			let decrypt4096 = await jwt4096.decrypt(encrypt4096);
		}
		console.log('decrypt4096: ' + (Date.now() - t) + ' ms');
	}

	async encrypt8192() {
		let t = Date.now();
		for (let i = 0; i < max; i++) {
			let encrypt8192 = await jwt8192.encrypt(str);
			let decrypt8192 = await jwt8192.decrypt(encrypt8192);
		}
		console.log('decrypt8192: ' + (Date.now() - t) + ' ms');
	}
}