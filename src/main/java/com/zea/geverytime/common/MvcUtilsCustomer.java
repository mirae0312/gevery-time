package com.zea.geverytime.common;
 
 
 
 
public class MvcUtilsCustomer {
	  
	public static String getPagebar(int cPage, int numPerPage, int totalContent, String url) {
		//계속 String을 더해가야하기 때문에 StringBuilder로 만든다
		StringBuilder pagebar = new StringBuilder(); //문자열이 쌓여질 객체
		url = url + "?cPage="; // pageNo 추가전 상태로 미리 만들어두기
		
		final int pagebarSize = 5;
		final int totalPage = (int) Math.ceil((double) totalContent / numPerPage);
		final int pageStart = (cPage - 1) / pagebarSize * pagebarSize + 1;
		int pageEnd = pageStart + pagebarSize - 1;
		pageEnd = totalPage < pageEnd ? totalPage : pageEnd;
		int pageNo = pageStart;

		// [이전]
		if(pageNo == 1) {
			// cPage = 1, 2, 3, 4, 5일 때
		}
		else {
			pagebar.append("<a href='" + url + (pageNo - 1) + "'>prev</a>\n");
		}
		
		// pageNo
		while(pageNo <= pageEnd) {
			if(pageNo == cPage) {
				pagebar.append("<span class='cPage'>" + cPage + "</span>\n");
			}
			else {
				pagebar.append("<a href='" + url + pageNo + "'>" + pageNo + "</a>\n");
			}
			
			pageNo++;
		}
		
		
		// [다음]
		if(pageNo > totalPage) {
			
		}
		else {
			pagebar.append("<a href='" + url + pageNo + "'>next</a>\n");
		}
		
		return pagebar.toString();
	}

  
}
