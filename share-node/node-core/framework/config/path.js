module.exports = function () {
	return {
		dir: AppDir,
		controller: AppDir + '/controller',
		model: DaoDir + '/model',
		dao: DaoDir + '/dao',
		service: ServiceDir + '/service',
		util: CoreDir + '/framework/core/util',
		protocol: {
			msg: ProtocolDir + '/msg',
			base: ProtocolDir + '/base',
			httpRes: ProtocolDir + '/http/res',
			httpReq: ProtocolDir + '/http/req'
		}
	}
};