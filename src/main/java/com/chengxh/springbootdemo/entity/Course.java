package com.chengxh.springbootdemo.entity;

public class Course {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.c_id
     *
     * @mbg.generated Fri Mar 22 13:33:11 CST 2019
     */
    private String cId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.c_name
     *
     * @mbg.generated Fri Mar 22 13:33:11 CST 2019
     */
    private String cName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.t_id
     *
     * @mbg.generated Fri Mar 22 13:33:11 CST 2019
     */
    private String tId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.c_id
     *
     * @return the value of course.c_id
     *
     * @mbg.generated Fri Mar 22 13:33:11 CST 2019
     */
    public String getcId() {
        return cId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.c_id
     *
     * @param cId the value for course.c_id
     *
     * @mbg.generated Fri Mar 22 13:33:11 CST 2019
     */
    public void setcId(String cId) {
        this.cId = cId == null ? null : cId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.c_name
     *
     * @return the value of course.c_name
     *
     * @mbg.generated Fri Mar 22 13:33:11 CST 2019
     */
    public String getcName() {
        return cName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.c_name
     *
     * @param cName the value for course.c_name
     *
     * @mbg.generated Fri Mar 22 13:33:11 CST 2019
     */
    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.t_id
     *
     * @return the value of course.t_id
     *
     * @mbg.generated Fri Mar 22 13:33:11 CST 2019
     */
    public String gettId() {
        return tId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.t_id
     *
     * @param tId the value for course.t_id
     *
     * @mbg.generated Fri Mar 22 13:33:11 CST 2019
     */
    public void settId(String tId) {
        this.tId = tId == null ? null : tId.trim();
    }
}