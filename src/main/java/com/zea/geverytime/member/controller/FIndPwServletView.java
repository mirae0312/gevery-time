package com.zea.geverytime.member.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.member.model.service.MemberService;
import com.zea.geverytime.member.model.vo.Member;

/**
 * Servlet implementation class FIndPwSevletView
 */
@WebServlet("/member/FindPwServletView")
public class FIndPwServletView extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	MemberService memberService = new MemberService();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);
        String email = request.getParameter("email");
        System.out.println(email);
        //먼저 아이디로 회원정보를 받아오고 가져온 데이터에서 email값을 비교하여 존재하지 않으면 인증메일 보내지 못함
        Member m = new MemberService().memberCallPw(memberId);
       
        System.out.println(m);
        	
        if(m == null ||  !m.getEmail().equals(email) || !m.getMemberId().equals(memberId))
        {	
             request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
             .forward(request, response);
    
         	return;
	
        }		
		        String location = request.getContextPath() + "/";
		        response.sendRedirect(location);
        		//mail server 설정
                String host = "smtp.naver.com";
                String user = ""; //자신의 네이버 계정
                String password = "";//자신의 네이버 패스워드
                int port = 465;
                //메일 받을 주소
                String to_email = m.getEmail();
                System.out.println(to_email);
                
                
                //SMTP 서버 정보를 설정한다.
                Properties props = new Properties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", port);
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.ssl.enable", "true");
                props.put("mail.smtp.ssl.trust", host);
                //인증 번호 생성기
                StringBuffer temp =new StringBuffer();
                Random rnd = new Random();
                for(int i=0;i<10;i++)
                {
                    int rIndex = rnd.nextInt(3);
                    switch (rIndex) {
                    case 0:
                        // a-z
                        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                        break;
                    case 1:
                        // A-Z
                        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                        break;
                    case 2:
                        // 0-9
                        temp.append((rnd.nextInt(10)));
                        break;
                    }
                }
                String AuthenticationKey = temp.toString();
                System.out.println(AuthenticationKey);
                
                if(m.getMemberId().equals(memberId)) {
                m.setPassword(temp.toString());
             
                int member =  memberService.insertPassword(m);
                String message = member > 0 ? "초기화성공!" : "초기화실패!";
                
                
                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                	 String un=user;
                     String pw=password;
                	protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(un,pw);
                    }
                });
              
                session.setDebug(true); //for debug
                //email 전송
                try {
                    MimeMessage msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress("wonyoung2309@naver.com"));
                    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
                    
                    //메일 제목
                    msg.setSubject("안녕하세요 Geverytime 인증 메일입니다.");
                    //메일 내용
                    msg.setText("임시 비밀번호는 :"+temp);
                    
                    Transport.send(msg);
                    System.out.println("이메일 전송");
                
                }catch (Exception e) {
                    e.printStackTrace();// TODO: handle exception
                   
                }
                HttpSession saveKey = request.getSession();
                saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
              
                request.setAttribute("id", memberId);
                RequestDispatcher rd =  request.getRequestDispatcher("/WEB-INF/views/member/redirect.jsp");
                rd.include(request, response);
                }
				    
	}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	HttpSession session = request.getSession();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/searchPasswordStart.jsp");
			rd.forward(request, response);
			}

}
