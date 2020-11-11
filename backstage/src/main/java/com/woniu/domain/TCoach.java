package com.woniu.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zly
 * @since 2020-11-11
 */
@Builder
public class TCoach implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "coa_id")
    private Integer coaId;

    private Integer coaPhone;

    private Double price;

    private String factions;

    private Integer certificationStatus;

    private Integer morningStatus;

    private Integer afternoonStatus;

    private Integer nightStatus;

    private String coaName;

    private String coaPassword;

    private String coaHeadPortrait;

    private Double coaMoney;

    private Integer coaOpenid;

    private Integer coaStatus;

    private String coaAddress;

    private String attentionUser;

    private String attentionCoach;

    private String attentionVenues;

    private String coaEmail;

    private String belongsVenues;

    private Integer classFullStatus;

    private Integer privateStatus;

    private Integer showStatus;

    private Integer searchStatus;

    private Integer bankcard;

    private String truename;

    private Integer idcard;

    private String myUser;


    public Integer getCoaId() {
        return coaId;
    }

    public void setCoaId(Integer coaId) {
        this.coaId = coaId;
    }

    public Integer getCoaPhone() {
        return coaPhone;
    }

    public void setCoaPhone(Integer coaPhone) {
        this.coaPhone = coaPhone;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFactions() {
        return factions;
    }

    public void setFactions(String factions) {
        this.factions = factions;
    }

    public Integer getCertificationStatus() {
        return certificationStatus;
    }

    public void setCertificationStatus(Integer certificationStatus) {
        this.certificationStatus = certificationStatus;
    }

    public Integer getMorningStatus() {
        return morningStatus;
    }

    public void setMorningStatus(Integer morningStatus) {
        this.morningStatus = morningStatus;
    }

    public Integer getAfternoonStatus() {
        return afternoonStatus;
    }

    public void setAfternoonStatus(Integer afternoonStatus) {
        this.afternoonStatus = afternoonStatus;
    }

    public Integer getNightStatus() {
        return nightStatus;
    }

    public void setNightStatus(Integer nightStatus) {
        this.nightStatus = nightStatus;
    }

    public String getCoaName() {
        return coaName;
    }

    public void setCoaName(String coaName) {
        this.coaName = coaName;
    }

    public String getCoaPassword() {
        return coaPassword;
    }

    public void setCoaPassword(String coaPassword) {
        this.coaPassword = coaPassword;
    }

    public String getCoaHeadPortrait() {
        return coaHeadPortrait;
    }

    public void setCoaHeadPortrait(String coaHeadPortrait) {
        this.coaHeadPortrait = coaHeadPortrait;
    }

    public Double getCoaMoney() {
        return coaMoney;
    }

    public void setCoaMoney(Double coaMoney) {
        this.coaMoney = coaMoney;
    }

    public Integer getCoaOpenid() {
        return coaOpenid;
    }

    public void setCoaOpenid(Integer coaOpenid) {
        this.coaOpenid = coaOpenid;
    }

    public Integer getCoaStatus() {
        return coaStatus;
    }

    public void setCoaStatus(Integer coaStatus) {
        this.coaStatus = coaStatus;
    }

    public String getCoaAddress() {
        return coaAddress;
    }

    public void setCoaAddress(String coaAddress) {
        this.coaAddress = coaAddress;
    }

    public String getAttentionUser() {
        return attentionUser;
    }

    public void setAttentionUser(String attentionUser) {
        this.attentionUser = attentionUser;
    }

    public String getAttentionCoach() {
        return attentionCoach;
    }

    public void setAttentionCoach(String attentionCoach) {
        this.attentionCoach = attentionCoach;
    }

    public String getAttentionVenues() {
        return attentionVenues;
    }

    public void setAttentionVenues(String attentionVenues) {
        this.attentionVenues = attentionVenues;
    }

    public String getCoaEmail() {
        return coaEmail;
    }

    public void setCoaEmail(String coaEmail) {
        this.coaEmail = coaEmail;
    }

    public String getBelongsVenues() {
        return belongsVenues;
    }

    public void setBelongsVenues(String belongsVenues) {
        this.belongsVenues = belongsVenues;
    }

    public Integer getClassFullStatus() {
        return classFullStatus;
    }

    public void setClassFullStatus(Integer classFullStatus) {
        this.classFullStatus = classFullStatus;
    }

    public Integer getPrivateStatus() {
        return privateStatus;
    }

    public void setPrivateStatus(Integer privateStatus) {
        this.privateStatus = privateStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(Integer searchStatus) {
        this.searchStatus = searchStatus;
    }

    public Integer getBankcard() {
        return bankcard;
    }

    public void setBankcard(Integer bankcard) {
        this.bankcard = bankcard;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public Integer getIdcard() {
        return idcard;
    }

    public void setIdcard(Integer idcard) {
        this.idcard = idcard;
    }

    public String getMyUser() {
        return myUser;
    }

    public void setMyUser(String myUser) {
        this.myUser = myUser;
    }

    @Override
    public String toString() {
        return "TCoach{" +
        "coaId=" + coaId +
        ", coaPhone=" + coaPhone +
        ", price=" + price +
        ", factions=" + factions +
        ", certificationStatus=" + certificationStatus +
        ", morningStatus=" + morningStatus +
        ", afternoonStatus=" + afternoonStatus +
        ", nightStatus=" + nightStatus +
        ", coaName=" + coaName +
        ", coaPassword=" + coaPassword +
        ", coaHeadPortrait=" + coaHeadPortrait +
        ", coaMoney=" + coaMoney +
        ", coaOpenid=" + coaOpenid +
        ", coaStatus=" + coaStatus +
        ", coaAddress=" + coaAddress +
        ", attentionUser=" + attentionUser +
        ", attentionCoach=" + attentionCoach +
        ", attentionVenues=" + attentionVenues +
        ", coaEmail=" + coaEmail +
        ", belongsVenues=" + belongsVenues +
        ", classFullStatus=" + classFullStatus +
        ", privateStatus=" + privateStatus +
        ", showStatus=" + showStatus +
        ", searchStatus=" + searchStatus +
        ", bankcard=" + bankcard +
        ", truename=" + truename +
        ", idcard=" + idcard +
        ", myUser=" + myUser +
        "}";
    }
}
