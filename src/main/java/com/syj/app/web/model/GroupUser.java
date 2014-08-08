package com.syj.app.web.model;

public class GroupUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_user.id:
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_user.master_id:Ⱥ��ID
     *
     * @mbggenerated
     */
    private Long masterId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_user.user_id:�û�ID
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_user.user_account:�û��˺�
     *
     * @mbggenerated
     */
    private String userAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_user.user_name:�û�����
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_user.group_id:Ⱥ��ID
     *
     * @mbggenerated
     */
    private Long groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_user.user_image:�û�ͷ��
     *
     * @mbggenerated
     */
    private String userImage;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_user.id
     *
     * @return the value of group_user.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_user.id
     *
     * @param id the value for group_user.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_user.master_id
     *
     * @return the value of group_user.master_id
     *
     * @mbggenerated
     */
    public Long getMasterId() {
        return masterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_user.master_id
     *
     * @param masterId the value for group_user.master_id
     *
     * @mbggenerated
     */
    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_user.user_id
     *
     * @return the value of group_user.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_user.user_id
     *
     * @param userId the value for group_user.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_user.user_account
     *
     * @return the value of group_user.user_account
     *
     * @mbggenerated
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_user.user_account
     *
     * @param userAccount the value for group_user.user_account
     *
     * @mbggenerated
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_user.user_name
     *
     * @return the value of group_user.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_user.user_name
     *
     * @param userName the value for group_user.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_user.group_id
     *
     * @return the value of group_user.group_id
     *
     * @mbggenerated
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_user.group_id
     *
     * @param groupId the value for group_user.group_id
     *
     * @mbggenerated
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_user.user_image
     *
     * @return the value of group_user.user_image
     *
     * @mbggenerated
     */
    public String getUserImage() {
        return userImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_user.user_image
     *
     * @param userImage the value for group_user.user_image
     *
     * @mbggenerated
     */
    public void setUserImage(String userImage) {
        this.userImage = userImage == null ? null : userImage.trim();
    }
}