package com.linhuanjie.admin.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Table:
 *   miao_user
 *
 * @author Ant丶
 * @mbg.generated
 */
public class MiaoUser extends MiaoUserKey implements Serializable {
    /**
     * Description:
     *   用户名
     *
     * Column:
     *   miao_user.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * Description:
     *   密码
     *
     * Column:
     *   miao_user.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     * Description:
     *   邮箱
     *
     * Column:
     *   miao_user.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     * Description:
     *   创建时间
     *
     * Column:
     *   miao_user.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * Description:
     *   更新时间
     *
     * Column:
     *   miao_user.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * Description:
     *   角色id
     *
     * Column:
     *   miao_user.salt
     *
     * @mbg.generated
     */
    private String salt;

    /**
     * Description:
     *   用户状态
     *
     * Column:
     *   miao_user.user_status
     *
     * @mbg.generated
     */
    private Short userStatus;

    /**
     * Description:
     *  This field corresponds to the database table miao_user
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * Description:
     *  This field corresponds to the database table miao_user
     *
     * @mbg.generated
     */
    private Map<String, Boolean> selectiveColumns = new HashMap<String, Boolean>();

    /**
     * Description:
     *  返回 miao_user.user_name 的值
     *
     * @return 字段 miao_user.user_name 的值
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Description:
     *  设置 miao_user.user_name 的值.
     *
     * @param userName 字段 miao_user.user_name 的值.
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Description:
     *  返回 miao_user.password 的值
     *
     * @return 字段 miao_user.password 的值
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * Description:
     *  设置 miao_user.password 的值.
     *
     * @param password 字段 miao_user.password 的值.
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Description:
     *  返回 miao_user.email 的值
     *
     * @return 字段 miao_user.email 的值
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * Description:
     *  设置 miao_user.email 的值.
     *
     * @param email 字段 miao_user.email 的值.
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Description:
     *  返回 miao_user.create_time 的值
     *
     * @return 字段 miao_user.create_time 的值
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Description:
     *  设置 miao_user.create_time 的值.
     *
     * @param createTime 字段 miao_user.create_time 的值.
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Description:
     *  返回 miao_user.update_time 的值
     *
     * @return 字段 miao_user.update_time 的值
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Description:
     *  设置 miao_user.update_time 的值.
     *
     * @param updateTime 字段 miao_user.update_time 的值.
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Description:
     *  返回 miao_user.salt 的值
     *
     * @return 字段 miao_user.salt 的值
     *
     * @mbg.generated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Description:
     *  设置 miao_user.salt 的值.
     *
     * @param salt 字段 miao_user.salt 的值.
     *
     * @mbg.generated
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Description:
     *  返回 miao_user.user_status 的值
     *
     * @return 字段 miao_user.user_status 的值
     *
     * @mbg.generated
     */
    public Short getUserStatus() {
        return userStatus;
    }

    /**
     * Description:
     *  设置 miao_user.user_status 的值.
     *
     * @param userStatus 字段 miao_user.user_status 的值.
     *
     * @mbg.generated
     */
    public void setUserStatus(Short userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return toString 的结果.
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", salt=").append(salt);
        sb.append(", userStatus=").append(userStatus);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return builder 的结果.
     * @mbg.generated
     */
    public static MiaoUser.Builder builder() {
        return new MiaoUser.Builder();
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return hasSelective 的结果.
     * @mbg.generated
     */
    public boolean hasSelective() {
        return this.selectiveColumns.size() > 0;
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return hasSelective 的结果.
     * @mbg.generated
     */
    public boolean hasSelective(String column) {
        return this.selectiveColumns.get(column) != null;
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return selective 的结果.
     * @mbg.generated
     */
    public MiaoUser selective(Column ... columns) {
        this.selectiveColumns.clear();
        if (columns != null) {
            for (Column column : columns) {
                this.selectiveColumns.put(column.value(), true);
            }
        }
        return this;
    }

    /**
     * Description:
     *  This class corresponds to the database table miao_user
     *
     * @author Ant丶
     * @mbg.generated
     */
    public static class Builder extends MiaoUserKey.Builder {
        /**
         * Description:
         *  This field corresponds to the database table miao_user
         *
         * @mbg.generated
         */
        private MiaoUser obj;

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return Builder 的结果.
         * @mbg.generated
         */
        public Builder() {
            this.obj = new MiaoUser();
        }

        /**
         * Description:
         *  设置 miao_user.user_id 的值.
         *
         * @param userId 字段 miao_user.user_id 的值.
         *
         * @mbg.generated
         */
        public Builder userId(Integer userId) {
            obj.setUserId(userId);
            return this;
        }

        /**
         * Description:
         *  设置 miao_user.user_name 的值.
         *
         * @param userName 字段 miao_user.user_name 的值.
         *
         * @mbg.generated
         */
        public Builder userName(String userName) {
            obj.setUserName(userName);
            return this;
        }

        /**
         * Description:
         *  设置 miao_user.password 的值.
         *
         * @param password 字段 miao_user.password 的值.
         *
         * @mbg.generated
         */
        public Builder password(String password) {
            obj.setPassword(password);
            return this;
        }

        /**
         * Description:
         *  设置 miao_user.email 的值.
         *
         * @param email 字段 miao_user.email 的值.
         *
         * @mbg.generated
         */
        public Builder email(String email) {
            obj.setEmail(email);
            return this;
        }

        /**
         * Description:
         *  设置 miao_user.create_time 的值.
         *
         * @param createTime 字段 miao_user.create_time 的值.
         *
         * @mbg.generated
         */
        public Builder createTime(Date createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        /**
         * Description:
         *  设置 miao_user.update_time 的值.
         *
         * @param updateTime 字段 miao_user.update_time 的值.
         *
         * @mbg.generated
         */
        public Builder updateTime(Date updateTime) {
            obj.setUpdateTime(updateTime);
            return this;
        }

        /**
         * Description:
         *  设置 miao_user.salt 的值.
         *
         * @param salt 字段 miao_user.salt 的值.
         *
         * @mbg.generated
         */
        public Builder salt(String salt) {
            obj.setSalt(salt);
            return this;
        }

        /**
         * Description:
         *  设置 miao_user.user_status 的值.
         *
         * @param userStatus 字段 miao_user.user_status 的值.
         *
         * @mbg.generated
         */
        public Builder userStatus(Short userStatus) {
            obj.setUserStatus(userStatus);
            return this;
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return build 的结果.
         * @mbg.generated
         */
        public MiaoUser build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table miao_user
     *
     * @mbg.generated
     */
    public enum Column {
        userId("user_id"),
        userName("user_name"),
        password("password"),
        email("email"),
        createTime("create_time"),
        updateTime("update_time"),
        salt("salt"),
        userStatus("user_status");

        /**
         * Description:
         *  This field corresponds to the database table miao_user
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return value 的结果.
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return getValue 的结果.
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @param  column column
         * @return Column 的结果.
         * @mbg.generated
         */
        Column(String column) {
            this.column = column;
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return desc 的结果.
         * @mbg.generated
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return asc 的结果.
         * @mbg.generated
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}