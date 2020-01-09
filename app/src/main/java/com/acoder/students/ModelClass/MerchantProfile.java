package com.acoder.students.ModelClass;

import android.media.Rating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.text.WordUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by SHAKI on 30-Mar-18.
 */
public class MerchantProfile implements Serializable {
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("fbPage")
    @Expose
    private String fbPage;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("priceTag")
    @Expose
    private Integer priceTag;
    @SerializedName("distance_in_km")
    @Expose
    private String distanceInKm;
    @SerializedName("location_lat")
    @Expose
    private Double locationLat;
    @SerializedName("location_lon")
    @Expose
    private Double locationLon;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("discount_text")
    @Expose
    private String discountText;
    @SerializedName("discount_low")
    @Expose
    private String discountLow;
    @SerializedName("discount_mid")
    @Expose
    private String discountMid;
    @SerializedName("discount_high")
    @Expose
    private String discountHigh;
    @SerializedName("minimum_cost")
    @Expose
    private String minimumCost;
    @SerializedName("shop_type")
    @Expose
    private String shopType;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("sortDescription")
    @Expose
    private String sortDescription;
    @SerializedName("contact_number")
    @Expose
    private String phoneNo;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("min_voucher")
    @Expose
    private String minVoucher;
    @SerializedName("short_address")
    @Expose
    private String shortAddress;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("is_favorite")
    @Expose
    private boolean isFavorite;
    @SerializedName("is_open")
    @Expose
    private boolean isOpen;
    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("specialities")
    @Expose
    private ArrayList<String> specialities = null;
    @SerializedName("budget_ranges")
    @Expose
    private ArrayList<String> budgetRange = null;
    @SerializedName("budget_average")
    @Expose
    private ArrayList<String> budgetAverage = null;

    private boolean joiningEvent;


    public String getCategory() {
        if (category == null)
            return "";
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTags() {
        return WordUtils.capitalizeFully(tags);
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFbPage() {
        return fbPage;
    }

    public void setFbPage(String fbPage) {
        this.fbPage = fbPage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceTag() {
        return priceTag;
    }

    public void setPriceTag(Integer priceTag) {
        this.priceTag = priceTag;
    }

    public Double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    public Double getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(Double locationLon) {
        this.locationLon = locationLon;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        if (name == null)
            return "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        if (thumbnail == null)
            return "";
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription;
    }

    public String getPhoneNo() {
        if (phoneNo == null)
            return "";
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        if (address == null)
            return "";
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    public String getDiscountLow() {
        return discountLow;
    }

    public void setDiscountLow(String discountLow) {
        this.discountLow = discountLow;
    }

    public String getDiscountMid() {
        return discountMid;
    }

    public void setDiscountMid(String discountMid) {
        this.discountMid = discountMid;
    }

    public String getDiscountHigh() {
        return discountHigh;
    }

    public void setDiscountHigh(String discountHigh) {
        this.discountHigh = discountHigh;
    }

    public String getMinimumCost() {
        return minimumCost;
    }

    public void setMinimumCost(String minimumCost) {
        this.minimumCost = minimumCost;
    }

    public String getCover() {
        if (cover == null)
            return "";
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setDistanceInKm(String distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public String getDistanceInKm() {

        if (distanceInKm != null) {
            if(distanceInKm.isEmpty()){
                return "";
            }
            float f = Float.parseFloat(distanceInKm);
            if (f > 1000) {
                return "";
            }
            return String.format(" (%.1f km away)", f);
        } else {
            return "";
        }
    }

    public double getDistanceInDouble(){
        if (distanceInKm != null) {
            if(distanceInKm.isEmpty()){
                return 100;
            }
            float f = Float.parseFloat(distanceInKm);
            if (f > 1000) {
                return 100;
            }
            return Double.parseDouble(distanceInKm);
        } else {
            return 100;
        }
    }

    public String getShortAddress() {
        try {
            shortAddress = shortAddress.substring(0, 1).toUpperCase() + shortAddress.substring(1);
        } catch (Exception e) {
        }
        if (shortAddress != null)
            return shortAddress;
        else
            return "";
    }

    public void setShortAddress(String shortAddress) {
        this.shortAddress = shortAddress;
    }

    public ArrayList<String> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(ArrayList<String> specialities) {
        this.specialities = specialities;
    }

    public ArrayList<String> getBudgetRange() {

        if(budgetRange == null)
            return new ArrayList<String >();
        return budgetRange;
    }

    public void setBudgetRange(ArrayList<String> budgetRange) {
        this.budgetRange = budgetRange;
    }

    public String getNote() {
        if (note == null)
            return "";
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<String> getBudgetAverage() {
        return budgetAverage;
    }

    public void setBudgetAverage(ArrayList<String> budgetAverage) {
        this.budgetAverage = budgetAverage;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public boolean isJoiningEvent() {
        return joiningEvent;
    }

    public void setJoiningEvent(boolean joiningEvent) {
        this.joiningEvent = joiningEvent;
    }

    public String getDiscountText() {
        return discountText;
    }

    public void setDiscountText(String discountText) {
        this.discountText = discountText;
    }

    public String getMinVoucher() {
        return minVoucher;
    }

    public void setMinVoucher(String minVoucher) {
        this.minVoucher = minVoucher;
    }




}
