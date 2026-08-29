/**
 *
 */
package com.pde.uweb.admin.controller;

import com.pde.uweb.ma.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author lifengyang
 */
@Controller
@RequestMapping("/cdkey")
public class CDKeyController {
    @Resource(name = "memberService_biz")
    private MemberService memberService;

}
