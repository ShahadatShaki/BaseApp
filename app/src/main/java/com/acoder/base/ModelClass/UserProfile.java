package com.acoder.base.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("is_agency")
    @Expose
    private String isAgency;
    @SerializedName("agency_member_code")
    @Expose
    private String agencyMemberCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("ref_code")
    @Expose
    private String refCode;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("othersRegistation")
    @Expose
    private String othersRegistation;
    @SerializedName("others_id")
    @Expose
    private String othersId;
    @SerializedName("api_access_token")
    @Expose
    private String apiAccessToken;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;

    public String getId() {

        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsAgency() {

        return isAgency == null ? "" : isAgency;
    }

    public void setIsAgency(String isAgency) {
        this.isAgency = isAgency;
    }

    public String getAgencyMemberCode() {

        return agencyMemberCode == null ? "" : agencyMemberCode;
    }

    public void setAgencyMemberCode(String agencyMemberCode) {
        this.agencyMemberCode = agencyMemberCode;
    }

    public String getName() {

        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {

        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRefCode() {

        return refCode == null ? "" : refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getCreatedAt() {

        return createdAt == null ? "" : createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {

        return updatedAt == null ? "" : updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getMobileNo() {

        return mobileNo == null ? "" : mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStatus() {

        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {

        return photo == null ? "" : photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {

        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {

        return city == null ? "" : city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {

        return postalCode == null ? "" : postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {

        return state == null ? "" : state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOthersRegistation() {

        return othersRegistation == null ? "" : othersRegistation;
    }

    public void setOthersRegistation(String othersRegistation) {
        this.othersRegistation = othersRegistation;
    }

    public String getOthersId() {

        return othersId == null ? "" : othersId;
    }

    public void setOthersId(String othersId) {
        this.othersId = othersId;
    }

    public String getApiAccessToken() {

        return apiAccessToken == null ? "" : apiAccessToken;
    }

    public void setApiAccessToken(String apiAccessToken) {
        this.apiAccessToken = apiAccessToken;
    }

    public String getGender() {

        return gender == null ? "" : gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {

        return dateOfBirth == null ? "" : dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
