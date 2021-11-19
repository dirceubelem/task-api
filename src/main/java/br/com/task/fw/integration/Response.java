package br.com.task.fw.integration;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.json.JSONObject;

/**
 * @author dirceubelem
 */
public class Response extends Data {

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getJSON() {
        try {
            JSONObject j = new JSONObject(getBody());
            return j;
        } catch (Exception e) {
            return null;
        }
    }

}
