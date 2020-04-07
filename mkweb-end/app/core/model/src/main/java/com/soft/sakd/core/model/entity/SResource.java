package com.soft.sakd.core.model.entity;

import java.io.Serializable;
import java.util.Date;

public class SResource implements Serializable {
    private Long id;

    private Date createAt;

    private Integer createBy;

    private Integer isDeleted;

    private Date updateAt;

    private Integer updateBy;

    private String iconSclass;

    private String includeUri;

    private String name;

    private String notes;

    private Integer sortNum;

    private Integer optlock;

    private Long parentId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getIconSclass() {
        return iconSclass;
    }

    public void setIconSclass(String iconSclass) {
        this.iconSclass = iconSclass == null ? null : iconSclass.trim();
    }

    public String getIncludeUri() {
        return includeUri;
    }

    public void setIncludeUri(String includeUri) {
        this.includeUri = includeUri == null ? null : includeUri.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getOptlock() {
        return optlock;
    }

    public void setOptlock(Integer optlock) {
        this.optlock = optlock;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createAt=").append(createAt);
        sb.append(", createBy=").append(createBy);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", updateAt=").append(updateAt);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", iconSclass=").append(iconSclass);
        sb.append(", includeUri=").append(includeUri);
        sb.append(", name=").append(name);
        sb.append(", notes=").append(notes);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", optlock=").append(optlock);
        sb.append(", parentId=").append(parentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}