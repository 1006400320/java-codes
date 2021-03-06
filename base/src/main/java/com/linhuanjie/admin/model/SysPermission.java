package com.linhuanjie.admin.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Table:
 *   sys_permission
 *
 * @author Ant丶
 * @mbg.generated
 */
public class SysPermission extends SysPermissionKey implements Serializable {
    /**
     *
     * Column:
     *   sys_permission.parent_id
     *
     * @mbg.generated
     */
    private Long parentId;

    /**
     *
     * Column:
     *   sys_permission.res_name
     *
     * @mbg.generated
     */
    private String resName;

    /**
     *
     * Column:
     *   sys_permission.res_type
     *
     * @mbg.generated
     */
    private String resType;

    /**
     *
     * Column:
     *   sys_permission.permission
     *
     * @mbg.generated
     */
    private String permission;

    /**
     *
     * Column:
     *   sys_permission.url
     *
     * @mbg.generated
     */
    private String url;

    /**
     * Description:
     *  This field corresponds to the database table sys_permission
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * Description:
     *  This field corresponds to the database table sys_permission
     *
     * @mbg.generated
     */
    private Map<String, Boolean> selectiveColumns = new HashMap<String, Boolean>();

    /**
     * Description:
     *  返回 sys_permission.parent_id 的值
     *
     * @return 字段 sys_permission.parent_id 的值
     *
     * @mbg.generated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Description:
     *  设置 sys_permission.parent_id 的值.
     *
     * @param parentId 字段 sys_permission.parent_id 的值.
     *
     * @mbg.generated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * Description:
     *  返回 sys_permission.res_name 的值
     *
     * @return 字段 sys_permission.res_name 的值
     *
     * @mbg.generated
     */
    public String getResName() {
        return resName;
    }

    /**
     * Description:
     *  设置 sys_permission.res_name 的值.
     *
     * @param resName 字段 sys_permission.res_name 的值.
     *
     * @mbg.generated
     */
    public void setResName(String resName) {
        this.resName = resName;
    }

    /**
     * Description:
     *  返回 sys_permission.res_type 的值
     *
     * @return 字段 sys_permission.res_type 的值
     *
     * @mbg.generated
     */
    public String getResType() {
        return resType;
    }

    /**
     * Description:
     *  设置 sys_permission.res_type 的值.
     *
     * @param resType 字段 sys_permission.res_type 的值.
     *
     * @mbg.generated
     */
    public void setResType(String resType) {
        this.resType = resType;
    }

    /**
     * Description:
     *  返回 sys_permission.permission 的值
     *
     * @return 字段 sys_permission.permission 的值
     *
     * @mbg.generated
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Description:
     *  设置 sys_permission.permission 的值.
     *
     * @param permission 字段 sys_permission.permission 的值.
     *
     * @mbg.generated
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * Description:
     *  返回 sys_permission.url 的值
     *
     * @return 字段 sys_permission.url 的值
     *
     * @mbg.generated
     */
    public String getUrl() {
        return url;
    }

    /**
     * Description:
     *  设置 sys_permission.url 的值.
     *
     * @param url 字段 sys_permission.url 的值.
     *
     * @mbg.generated
     */
    public void setUrl(String url) {
        this.url = url;
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
        sb.append(", parentId=").append(parentId);
        sb.append(", resName=").append(resName);
        sb.append(", resType=").append(resType);
        sb.append(", permission=").append(permission);
        sb.append(", url=").append(url);
        sb.append("]");
        return sb.toString();
    }

    /**
     * Description: This method was generated by MyBatis Generator.
     *
     * @return builder 的结果.
     * @mbg.generated
     */
    public static SysPermission.Builder builder() {
        return new SysPermission.Builder();
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
    public SysPermission selective(Column ... columns) {
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
     *  This class corresponds to the database table sys_permission
     *
     * @author Ant丶
     * @mbg.generated
     */
    public static class Builder extends SysPermissionKey.Builder {
        /**
         * Description:
         *  This field corresponds to the database table sys_permission
         *
         * @mbg.generated
         */
        private SysPermission obj;

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return Builder 的结果.
         * @mbg.generated
         */
        public Builder() {
            this.obj = new SysPermission();
        }

        /**
         * Description:
         *  设置 sys_permission.id 的值.
         *
         * @param id 字段 sys_permission.id 的值.
         *
         * @mbg.generated
         */
        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        /**
         * Description:
         *  设置 sys_permission.parent_id 的值.
         *
         * @param parentId 字段 sys_permission.parent_id 的值.
         *
         * @mbg.generated
         */
        public Builder parentId(Long parentId) {
            obj.setParentId(parentId);
            return this;
        }

        /**
         * Description:
         *  设置 sys_permission.res_name 的值.
         *
         * @param resName 字段 sys_permission.res_name 的值.
         *
         * @mbg.generated
         */
        public Builder resName(String resName) {
            obj.setResName(resName);
            return this;
        }

        /**
         * Description:
         *  设置 sys_permission.res_type 的值.
         *
         * @param resType 字段 sys_permission.res_type 的值.
         *
         * @mbg.generated
         */
        public Builder resType(String resType) {
            obj.setResType(resType);
            return this;
        }

        /**
         * Description:
         *  设置 sys_permission.permission 的值.
         *
         * @param permission 字段 sys_permission.permission 的值.
         *
         * @mbg.generated
         */
        public Builder permission(String permission) {
            obj.setPermission(permission);
            return this;
        }

        /**
         * Description:
         *  设置 sys_permission.url 的值.
         *
         * @param url 字段 sys_permission.url 的值.
         *
         * @mbg.generated
         */
        public Builder url(String url) {
            obj.setUrl(url);
            return this;
        }

        /**
         * Description: This method was generated by MyBatis Generator.
         *
         * @return build 的结果.
         * @mbg.generated
         */
        public SysPermission build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table sys_permission
     *
     * @mbg.generated
     */
    public enum Column {
        id("id"),
        parentId("parent_id"),
        resName("res_name"),
        resType("res_type"),
        permission("permission"),
        url("url");

        /**
         * Description:
         *  This field corresponds to the database table sys_permission
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