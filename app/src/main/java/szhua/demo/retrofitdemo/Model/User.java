package szhua.demo.retrofitdemo.Model;


/**
 * 实体类的填写，将属性填写完毕以后直接使用工具生成get（）set（）方法即可；
 */

public class User {
	private String id;// 用户主键
	protected String username;// 手机号码 此为登录手机和用户名账户（一旦注册不能更改）
	protected String email;//用户邮箱
	protected String nickname;// 用户昵称
	protected String mobile;//手机号码
	protected String password;// 登陆密码 服务器端存储的是32位的MD5加密串
	protected String charindex;//用户昵称的汉语拼音首字母索引
	protected String sex;//用户性别
	protected String avatar;//个人主页头像图片(小)
	protected String avatarbig;//个人主页头像图片(大)
	protected String district_name;//注册地区
	protected String lng;//经度
	protected String lat;//维度
	protected String regdate;//用户注册时间
	protected String score;//用户积分
	protected String feeaccount;//账户余额
	protected String token;//	登陆令牌,由系统随机生成，作为后续请求服务的必传字段。
	protected String android_must_update;//安卓强制更新标记
	protected String android_last_version;//安卓最新版本号
	protected String android_update_url;//安卓软件更新地址
	private String age ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCharindex() {
		return charindex;
	}

	public void setCharindex(String charindex) {
		this.charindex = charindex;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatarbig() {
		return avatarbig;
	}

	public void setAvatarbig(String avatarbig) {
		this.avatarbig = avatarbig;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getFeeaccount() {
		return feeaccount;
	}

	public void setFeeaccount(String feeaccount) {
		this.feeaccount = feeaccount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAndroid_must_update() {
		return android_must_update;
	}

	public void setAndroid_must_update(String android_must_update) {
		this.android_must_update = android_must_update;
	}

	public String getAndroid_last_version() {
		return android_last_version;
	}

	public void setAndroid_last_version(String android_last_version) {
		this.android_last_version = android_last_version;
	}

	public String getAndroid_update_url() {
		return android_update_url;
	}

	public void setAndroid_update_url(String android_update_url) {
		this.android_update_url = android_update_url;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", nickname='" + nickname + '\'' +
				", mobile='" + mobile + '\'' +
				", password='" + password + '\'' +
				", charindex='" + charindex + '\'' +
				", sex='" + sex + '\'' +
				", avatar='" + avatar + '\'' +
				", avatarbig='" + avatarbig + '\'' +
				", district_name='" + district_name + '\'' +
				", lng='" + lng + '\'' +
				", lat='" + lat + '\'' +
				", regdate='" + regdate + '\'' +
				", score='" + score + '\'' +
				", feeaccount='" + feeaccount + '\'' +
				", token='" + token + '\'' +
				", android_must_update='" + android_must_update + '\'' +
				", android_last_version='" + android_last_version + '\'' +
				", android_update_url='" + android_update_url + '\'' +
				", age='" + age + '\'' +
				'}';
	}
}
