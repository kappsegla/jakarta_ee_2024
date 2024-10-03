package org.fungover.demo.filter;


//@WebFilter("/*")
////Servlet Filter registered for all endpoints with /*
//public class IPFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Initialization code, if needed
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        String remoteAddr = httpRequest.getRemoteAddr();
//        if ("127.0.0.1".equals(remoteAddr) || "localhost".equals(remoteAddr) || "0:0:0:0:0:0:0:1".equals(remoteAddr)) {
//            chain.doFilter(request, response); // Allow the request to proceed
//        } else {
//            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
//        }
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup code, if needed
//    }
//}
