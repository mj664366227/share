var Key = function (redisKey, sqlKey, dbService) {
	if (!_.isString(sqlKey) || sqlKey.trim() == "") {
		sqlKey = "id";
	}
	if (!_.isObject(dbService)) {
		dbService = guSlaveDbService;
	}
	return {
		redisKey: redisKey,
		sqlKey: sqlKey,
		dbService: dbService
	}
};
module.exports = {
	DChargeLog: Key(KeyFactory._chargeLogKey, "orderId", guLogDbService),
	DUserAddress: Key(KeyFactory._userAddressKey, "userId"),
	DUser: Key(KeyFactory._userKey),
	DCompany: Key(KeyFactory._companyKey),
	DProject: Key(KeyFactory._projectKey),
	DProjectIdea: Key(KeyFactory._projectIdeaKey),
	DProjectIdeaFile: Key(KeyFactory._projectIdeaFileKey),
	DProjectIdeaLink: Key(KeyFactory._projectIdeaLinkKey),
	DProjectResearch: Key(KeyFactory._projectResearchKey),
	DProjectIdeaComment: Key(KeyFactory._projectIdeaCommentKey),
	DProjectIdeaAdded: Key(KeyFactory._projectIdeaAddedKey),
	DProjectResearchCommentKey: Key(KeyFactory._projectResearchCommentKey)
};