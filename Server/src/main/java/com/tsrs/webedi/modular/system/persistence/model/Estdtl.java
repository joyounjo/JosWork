package com.tsrs.webedi.modular.system.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 見積DTL情報
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
public class Estdtl extends Model<Estdtl> {

    private static final long serialVersionUID = 1L;

    /**
     * 見積DID
     */
	@TableId(value="est_did", type= IdType.AUTO)
	private Integer estDid;
    /**
     * 見積HID
     */
	@TableField("est_hid")
	private Integer estHid;
    /**
     * 稟議書ID
     */
	@TableField("appr_doc_id")
	private Integer apprDocId;
    /**
     * 登録者
     */
	@TableField(value = "sys_ins_user_id", fill = FieldFill.INSERT)
	private String sysInsUserId;
    /**
     * 登録日時
     */
	@TableField(value = "sys_ins_dt", fill = FieldFill.INSERT)
	private Date sysInsDt;
    /**
     * 登録機能ID
     */
	@TableField(value = "sys_ins_pg_id", fill = FieldFill.INSERT)
	private String sysInsPgId;
    /**
     * 更新者
     */
	@TableField(value = "sys_upd_user_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdUserId;
    /**
     * 更新日時
     */
	@TableField(value = "sys_upd_dt", fill = FieldFill.INSERT_UPDATE)
	private Date sysUpdDt;
    /**
     * 更新機能ID
     */
	@TableField(value = "sys_upd_pg_id", fill = FieldFill.INSERT_UPDATE)
	private String sysUpdPgId;


	public Integer getEstDid() {
		return estDid;
	}

	public void setEstDid(Integer estDid) {
		this.estDid = estDid;
	}

	public Integer getEstHid() {
		return estHid;
	}

	public void setEstHid(Integer estHid) {
		this.estHid = estHid;
	}

	public Integer getApprDocId() {
		return apprDocId;
	}

	public void setApprDocId(Integer apprDocId) {
		this.apprDocId = apprDocId;
	}

	public String getSysInsUserId() {
		return sysInsUserId;
	}

	public void setSysInsUserId(String sysInsUserId) {
		this.sysInsUserId = sysInsUserId;
	}

	public Date getSysInsDt() {
		return sysInsDt;
	}

	public void setSysInsDt(Date sysInsDt) {
		this.sysInsDt = sysInsDt;
	}

	public String getSysInsPgId() {
		return sysInsPgId;
	}

	public void setSysInsPgId(String sysInsPgId) {
		this.sysInsPgId = sysInsPgId;
	}

	public String getSysUpdUserId() {
		return sysUpdUserId;
	}

	public void setSysUpdUserId(String sysUpdUserId) {
		this.sysUpdUserId = sysUpdUserId;
	}

	public Date getSysUpdDt() {
		return sysUpdDt;
	}

	public void setSysUpdDt(Date sysUpdDt) {
		this.sysUpdDt = sysUpdDt;
	}

	public String getSysUpdPgId() {
		return sysUpdPgId;
	}

	public void setSysUpdPgId(String sysUpdPgId) {
		this.sysUpdPgId = sysUpdPgId;
	}

	@Override
	public Serializable pkVal() {
		return this.estDid;
	}

	@Override
	public String toString() {
		return "Estdtl{" +
			"estDid=" + estDid +
			", estHid=" + estHid +
			", apprDocId=" + apprDocId +
			", sysInsUserId=" + sysInsUserId +
			", sysInsDt=" + sysInsDt +
			", sysInsPgId=" + sysInsPgId +
			", sysUpdUserId=" + sysUpdUserId +
			", sysUpdDt=" + sysUpdDt +
			", sysUpdPgId=" + sysUpdPgId +
			"}";
	}
}
