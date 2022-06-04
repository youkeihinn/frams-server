package com.projects.modular.system.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class UEditorController {
	

	@RequestMapping("/uEditors")
    private String showPage(){
        return "ueditor.html";
    }
	
	
	/*@RequestMapping(value="/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }*/

	
	
	
	
    
}
