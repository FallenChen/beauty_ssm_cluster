
public class RouteController {

   /**
     * 根据路由获取优先级最高的路由记录
     * @param heads
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "/merchant_id/{merchantId}", method = RequestMethod
            .GET)
    public String getById(@RequestHeader Map<String, String> heads,
                          @PathVariable("merchantId") long merchantId) {
        LOG.info("heads: ");
        LOG.info("-------------------------------");
        LOG.info(heads.toString());
        LOG.info("-------------------------------");

        String result = "";
        String userAgent = heads.get("user-agent");
        if (userAgent.toLowerCase().contains("MicroMessenger".toLowerCase())) {
            result = "redirect:" + routesService.getHightestPriorityById(merchantId, GatewayType.WEIXIN_GATEWAY);
        } else if (userAgent.toLowerCase()
                .contains("AlipayClient".toLowerCase())) {
            result = "redirect:" + routesService.getHightestPriorityById(merchantId, GatewayType.ALIPAY_GATEWAY);
        } else {
            //TODO 404的页面其实还没有做
            result = "404";
        }
        return result;
    }

}
