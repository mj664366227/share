module.exports = CompanyBase;
function CompanyBase(company) {
	var clazz = _.keys(company);
	if (clazz.length != 1) {
		throw new Error("t is not a pojo class");
	}
	if (clazz[0] != "DCompany") {
		throw new Error("t is not a DCompany");
	}
	/**
	 * 企业id
	 */
	this.companyId = company.DCompany.id;
	/**
	 * 企业名称
	 */
	this.name = company.DCompany.name;
	/**
	 * 企业头像
	 */
	this.logoImage = company.DCompany.logoImage;
}