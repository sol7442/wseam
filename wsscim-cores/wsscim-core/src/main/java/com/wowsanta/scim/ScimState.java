package com.wowsanta.scim;

public enum ScimState {
	Ok(	200,"Ok"),
	Created(	201,"Created"),
	
	TemprorayRedirect(	307,"TemporaryRedirect"),
	PermanentRedirect(	308,"PermanentRedirect"),
	BadRequest(			400,"BadRequest"),
	Unauthorized(		401,"Unauthorized"),
	Forbidden(			403,"Forbidden"),
	NotFound(			404,"NotFound"),
	Conflict(			409,"Conflict"),
	PreconditionFailed(	412,"PreconditionFailed"),
	PayloadTooLarge(	413,"PayloadTooLarge"),
	InternalServerError(500,"InternalServerError"),
	NotImplemented(		501,"NotImplemented"),
	
	
	invalidSyntax(	400,"Bad Request", "invalidSyntax"),
	invalidFilter(	400,"Bad Request", "invalidFilter"),
	tooMany(		400,"Bad Request", "tooMany"),
	uniqueness(		400,"Bad Request", "uniqueness"),
	mutability(		400,"Bad Request", "mutability"),
	invalidPath(	400,"Bad Request", "invalidPath"),
	noTarget(		400,"Bad Request", "noTarget"),
	invalidValue(	400,"Bad Request", "invalidValue"),
	invalidVers(	400,"Bad Request", "invalidVers"),
	sensitive(		400,"Bad Request", "sensitive");
		
	
	private int status;
	private String scimType;
	private String detail;
	
	public void addDetail(String add) {
		this.detail= this.detail + ":" + add;
	}
	private ScimState(int status, String detail) {
		this.status = status;
		this.detail = detail;
	}
	
	private ScimState(int status, String type , String detail) {
		this.status 	= status;		
		this.scimType 	= type;
		this.detail 	= detail;
	}
	
	public int getStatus() {return this.status;}
	public String getScimType() {return this.scimType;}
	public String getDetail() {return this.detail;}
	public static ScimState getValue(int status, String type , String detail) {
		ScimState value = null;
		if(detail != null) {
			value = ScimState.valueOf(detail);
		}else {
			value = ScimState.valueOf(type);
		}
		return value;
	}
}
