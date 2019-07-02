package com.longdian.bean;

import java.util.HashSet;
import java.util.Set;

public class OprInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Integer oprId;
    private String oprName;
    private String loginId;
    private String loginPwd;
    private String email;
    private String phone;
    private String oprSts;
    private String head;
    private Integer depId;
    private String depName;
    private Integer orgId;
    private String orgName;
    /**
     * 角色
     */
    private Set<String> roles = new HashSet<String>(0);

    // 最后登录日期
    private String lastDt;
    // 最后登录时间
    private String lastTm;

    public Integer getOprId() {
        return oprId;
    }

    public void setOprId(Integer oprId) {
        this.oprId = oprId;
    }

    public String getOprName() {
        return oprName;
    }

    public void setOprName(String oprName) {
        this.oprName = oprName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOprSts() {
        return oprSts;
    }

    public void setOprSts(String oprSts) {
        this.oprSts = oprSts;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getLastDt() {
        return lastDt;
    }

    public void setLastDt(String lastDt) {
        this.lastDt = lastDt;
    }

    public String getLastTm() {
        return lastTm;
    }

    public void setLastTm(String lastTm) {
        this.lastTm = lastTm;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OprInfo [oprId=");
        builder.append(oprId);
        builder.append(", oprName=");
        builder.append(oprName);
        builder.append(", loginId=");
        builder.append(loginId);
        builder.append(", loginPwd=");
        builder.append(loginPwd);
        builder.append(", email=");
        builder.append(email);
        builder.append(", phone=");
        builder.append(phone);
        builder.append(", oprSts=");
        builder.append(oprSts);
        builder.append(", head=");
        builder.append(head);
        builder.append(", depId=");
        builder.append(depId);
        builder.append(", depName=");
        builder.append(depName);
        builder.append(", orgId=");
        builder.append(orgId);
        builder.append(", orgName=");
        builder.append(orgName);
        builder.append(", roles=");
        builder.append(roles);
        builder.append(", authRoles=");
        builder.append(", lastDt=");
        builder.append(lastDt);
        builder.append(", lastTm=");
        builder.append(lastTm);
        builder.append("]");
        return builder.toString();
    }
}
