package com.lizikj.api.controller.download;

import com.lizikj.login.interceptor.LoginExclude;
import com.lizikj.opt.dto.SysPosVersionDTO;
import com.lizikj.opt.enums.PosVersionPushObjectEnum;
import com.lizikj.opt.enums.PosVersionTypeEnum;
import com.lizikj.opt.facade.ISysPosVersionReadFacade;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael.Huang
 * @date 2018/5/23 11:41
 */
@Controller
@RequestMapping("/download/liaoke")
public class LiaoKeController {

    /**
     * @private
     */
    private static Logger logger = LoggerFactory.getLogger(LiaoKeController.class);

    /**
     * IOS撩客管家下载链接
     */
    private String IOS_LIAOKE_APP_URL = "https://itunes.apple.com/cn/app/%E6%92%A9%E5%AE%A2%E7%AE%A1%E5%AE%B6/id1170980898?mt=8";

    /**
     * IOS撩客POS下载链接
     */
    private String IOS_LIAOKE_POS_URL = "https://itunes.apple.com/cn/app/%E6%92%A9%E5%AE%A2/id1383689538?mt=8";

    @Autowired
    private ISysPosVersionReadFacade sysPosVersionReadFacade;

    @LoginExclude
    @RequestMapping(value = "/index/{type}",method = RequestMethod.GET)
    public String index(
            @PathVariable(value = "type", required = true) Integer type, HttpServletRequest request, HttpServletResponse response) {
        String agentStr = request.getHeader("User-Agent");
        agentStr = agentStr.toLowerCase();
//        Boolean isWeiXin = agentStr.indexOf("micromessenger") != -1 ? Boolean.TRUE : Boolean.FALSE;
        logger.debug("user-agent:%s", agentStr);

        String link = null;
        UserAgent userAgent = UserAgent.parseUserAgentString(agentStr);
        if (userAgent.getOperatingSystem().getGroup().equals(OperatingSystem.ANDROID)) {
            SysPosVersionDTO sysPosVersionDTO = null;
            if (type == 1) {//撩客管家
                sysPosVersionDTO = sysPosVersionReadFacade.getOfficialVersion(PosVersionPushObjectEnum.APP, PosVersionTypeEnum.RELEASE);
            } else if (type == 2) {//撩客pos
                sysPosVersionDTO = sysPosVersionReadFacade.getOfficialVersion(PosVersionPushObjectEnum.APP_POS, PosVersionTypeEnum.RELEASE);
            }
            if (sysPosVersionDTO != null) {
                link = sysPosVersionDTO.getVsersionUrl();
            }
        } else {//ios或其他类型的都使用IOS
            if (type == 1) {//撩客管家
                link = IOS_LIAOKE_APP_URL;
            } else if (type == 2) {//撩客pos
                link = IOS_LIAOKE_POS_URL;
            }
        }
        if (link != null) {
            return "redirect:" + link;
        }
        return "";
    }
}
