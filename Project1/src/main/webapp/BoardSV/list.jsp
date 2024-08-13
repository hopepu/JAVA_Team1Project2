<%@page import="DTO.ShowDTO"%>
<%@page import="DAO.ShowDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
	ShowDAO dao = new ShowDAO(application);
	
	Map<String, Object> search = new HashMap<String, Object>();
	
	String searchField = request.getParameter("searchField");
	System.out.println(searchField);
	String searchWord = request.getParameter("searchWord");
	System.out.println(searchWord);
	if(searchWord != null){
		search.put("searchField", searchField );
		search.put("searchWord", searchWord);
	}
	int totalCount = dao.selectCount(search);

	// 전체 페이지 수 계산
	int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE")); // 페이지에 출력되는 리스트 개수 10 개
	int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK")); // 한 화면에 보여지는 블럭 수 5 개
	//out.println(pageSize + " "+ blockPage);
	int totalPage = (int)Math.ceil((double)totalCount/pageSize); // 총 페이지 수
	// 총 페이지 수 : 11 <- Math.ceil(올림) <-  10.5 <- 105 / 10 | (페이징 처리 3단계)
	
	// 현재 페이지 코드
	int pageNum=1;
	String pageTemp = request.getParameter("pageNum"); // List.jsp?pageNum=?
	if(pageTemp != null && !pageTemp.equals("")){ // url 로 넘어온 값이 있다의 조건
		pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 적용
		
	}
	
	// 목록에 출력 할 게시글 범위 계산 (페이징처리 2단계)
	int start = (pageNum-1) * pageSize + 1; // 첫 게시물 번호
	int end = pageNum * pageSize; // 마지막 게시물 번호
	
	search.put("start", start);
	search.put("end", end); // Map 을 통해 검색 조건과 같은 타입으로 전달
	
	List<ShowDTO> lists = dao.selectList(search);
	dao.close();
%>   
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="utf-8" />
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>1조 팀 프로젝트</title>
</head>
<body>
   <% if(lists.isEmpty()) { %>
   <div class="contatiner px-4 px-lg-5 mt-5">
   		<h3 align="center">해당하는 정보가 없습니다.</h3>
   </div>
   <%  } else {   %>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">             
   	<%   
	   int virtualNum=0; // 가상번호
	   int countNum = 0; // 페이지 넘버
	   
	   for(ShowDTO show : lists){
		   virtualNum = totalCount--;
			virtualNum = totalCount - (((pageNum-1) * pageSize)+countNum++);
   %>                 
                    <div class="col mb-5">                      
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="resources/image/<%=show.getMain_jpg() %>.jpg" alt="<%=show.getTitle() %>" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h6 class="fw-bolder"><%=show.getTitle() %></h6>
                                    <div class="d-flex justify-content-center small text-warning mb-2"> <!-- 별점 -->
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <%
										if(show.getPlatform().contains("Netflix")){
									%>                     
                                    <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="30" height="30" viewBox="0 0 48 48">
									<path fill="#424242" d="M42,37c0,2.762-2.238,5-5,5H11c-2.761,0-5-2.238-5-5V11c0-2.762,2.239-5,5-5h26c2.762,0,5,2.238,5,5	V37z"></path><path fill="#b71c1c" d="M31,12h-5v23.5c2.5,0,4.986,0.512,5,0.5S31,12,31,12z"></path><path fill="#b71c1c" d="M17,12h5v23.5c-2.5,0-4.986,0.512-5,0.5S17,12,17,12z"></path><path fill="#e53935" d="M22,12h-5l9,23.5c2.5,0,4.986,0.512,5,0.5S22,12,22,12z"></path>
									</svg> 
									<!-- 넷플릭스 아이콘 -->
									<% } if(show.getPlatform().contains("Tving")) { %>
									<svg id="Layer_1" height="24" viewBox="0 0 24 25" width="24" xmlns="http://www.w3.org/2000/svg" data-name="Layer 1">
									<path d="m19 0h-14a5.006 5.006 0 0 0 -5 5v14a5.006 5.006 0 0 0 5 5h14a5.006 5.006 0 0 0 5-5v-14a5.006 5.006 0 0 0 -5-5zm-2 10a1 1 0 0 1 -1-1 1 1 0 0 0 -1-1h-2v8h1a1 1 0 0 1 0 2h-4a1 1 0 0 1 0-2h1v-8h-2a1 1 0 0 0 -1 1 1 1 0 0 1 -2 0 3 3 0 0 1 3-3h6a3 3 0 0 1 3 3 1 1 0 0 1 -1 1z"/></svg>                               	
                                	<!-- 티빙아이콘 -->
                                	<% } if(show.getPlatform().contains("Disney+")){ %>
                                	<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="30" height="30" viewBox="0 0 48 48">
									<radialGradient id="61BnZA7zWz8h3PPZepZwra_o7YMV0TFYOgR_gr1" cx="3.795" cy="-13.73" r="83.425" gradientUnits="userSpaceOnUse"><stop offset="0" stop-color="#23d9d6"></stop><stop offset=".159" stop-color="#22c7da"></stop><stop offset=".479" stop-color="#1e97e6"></stop><stop offset=".927" stop-color="#194bf8"></stop><stop offset="1" stop-color="#183efb"></stop></radialGradient><path fill="url(#61BnZA7zWz8h3PPZepZwra_o7YMV0TFYOgR_gr1)" d="M13.714,42C9.46,42,6,38.54,6,34.286V13.714C6,9.46,9.46,6,13.714,6h20.571	C38.54,6,42,9.46,42,13.714v20.571C42,38.54,38.54,42,34.286,42H13.714z"></path><path fill="#fff" d="M15.445,20.679c-2.644-1.289-3.57-1.454-5.619-1.322s-2.049,1.157-1.554,1.19 C8.768,20.58,8.9,20.613,8.9,20.613l0.1-0.23l-0.595-0.264c0,0,2.578-0.661,5.784,0.892s4.032,3.57,3.14,4.561 c-0.892,0.991-2.446,1.487-4.362,1.058C12.934,25.274,13,24.283,13,24.283s1.785-0.066,2.248,0.264s0.066,0.528-0.231,0.628 c0.43,0.264,0.76,0.364,1.058,0c0.297-0.364,0.628-1.917-3.007-1.818c-0.068-0.628,0.131-1.058-0.398-1.686 c-0.528-0.628-0.528,1.719-0.528,1.719s-1.355,0.198-2.479,0.991s2.313,2.71,2.313,2.71s0.066,0.86,0.562,0.958 C13.033,28.148,13,27.421,13,27.421s2.974,0.86,4.958-0.892C19.94,24.778,18.089,21.968,15.445,20.679z M12.008,26.397 c0,0-1.124-0.463-1.752-1.025s-0.43-0.661-0.132-0.727c0.297-0.066,1.454-0.364,1.95-0.364L12.008,26.397z"></path><path fill="#fff" d="M19.246,23.555c0,0-0.166,1.917-0.099,3.107c0.033,0.496,0.827,0.595,0.827,0.099 c0-0.496-0.033-2.479-0.099-2.876C19.807,23.489,19.444,23.158,19.246,23.555z"></path><path fill="#fff" d="M23.741,23.323c0,0-1.818-0.397-3.04,0.397c-0.595,0.397-0.43,1.124-0.099,1.157 c0.33,0.033,2.016,0.099,2.413,0.364s0.496,0.463,0.066,0.727s-1.388,0.397-1.785,0.264c-0.397-0.132-0.528-0.827,0.198-0.463 c0.727,0.364,2.049-0.066,1.157-0.364c-0.892-0.297-1.619-0.231-2.082,0.099c-0.463,0.33,0.166,1.025,0.694,1.256 s1.521,0.297,2.115-0.066c0.595-0.364,0.892-1.752-0.066-1.983s-1.554-0.198-1.983-0.264c-0.43-0.066-0.231-0.231,0.463-0.297 s1.19-0.099,1.785-0.198C24.17,23.852,24.203,23.423,23.741,23.323z"></path><path fill="#fff" d="M26.187,23.819c0.066,0.297,0.33,1.421,0.33,1.884c-0.33-0.496-0.86-1.487-1.322-1.95 c-0.463-0.463-0.595-0.066-0.595-0.066s-0.463,1.685-0.33,2.776c0.132,1.091,0.694,0.33,0.727,0 c0.033-0.33,0.099-1.421,0.099-1.719c0.297,0.661,0.76,1.421,1.124,1.785s0.727,0.132,0.86-0.198 c0.132-0.33,0.562-2.115-0.463-3.305C25.593,21.838,26.12,23.522,26.187,23.819z"></path><path fill="#fff" d="M29.722,25.835c-0.364,0.033-1.421,0.198-1.421,0.198l0.231-0.694c0,0,0.694,0.033,1.256-0.033 s0.166-0.694-0.033-0.694c-0.198,0-0.925,0-0.925,0l0.166-0.463c0,0,1.223-0.033,1.619-0.099c0.397-0.066-0.231-0.727-0.694-0.76 s-1.454,0.099-2.115,0.231s0.43,0.628,0.43,0.628s-0.099,0.33-0.231,0.562c-0.397,0.066-0.364,0.297-0.231,0.595 C27.51,26,27.377,26.86,28.138,27.124c0.76,0.264,1.652-0.43,2.016-0.727C30.516,26.098,30.086,25.802,29.722,25.835z"></path><path fill="#fff" d="M32.532,23.886c-0.496,0.496-1.355,1.785-1.355,1.785c0.099-0.86,0.33-1.091,0.86-1.95 c0.452-0.734-0.264-0.463-0.264-0.463s-1.685,1.421-0.958,3.04c-0.496,1.223-1.289,2.974-0.132,3.9 c0.297,0.198,0.198-0.397,0.297-0.727s0.43-2.016,0.76-2.479c0.793-0.066,2.215-0.364,2.446-2.512 C34.283,23.092,33.028,23.389,32.532,23.886z M33.556,24.976c-0.33,0.892-1.454,1.025-1.454,1.025s0.727-1.19,1.091-1.521 C33.556,24.15,33.887,24.084,33.556,24.976z"></path><path fill="#fff" d="M21.262,21.968c0.661-0.661,0.958-1.884-0.198-1.818c0,0-0.43-0.562-1.355-0.297 c-0.925,0.264-2.082,1.487-1.256,2.28c0,0.364,0.099,1.058,0.595,0.43C19.576,22.795,20.601,22.629,21.262,21.968z M18.75,21.605 c-0.099-0.496,0.166-0.958,1.025-1.124C19.774,20.482,18.882,21.208,18.75,21.605z M20.733,20.779 c0.925-0.364,0.628,0.528,0.132,0.892s-0.991,0.463-1.586,0.297C19.279,21.968,19.807,21.142,20.733,20.779z"></path><path fill="#fff" d="M39.89,24.282h-1.989c-0.041-0.658-0.12-1.359-0.266-2.02c-0.008-0.037-0.039-0.066-0.076-0.068 c-0.218-0.017-0.257-0.005-0.523,0.001c-0.073,0.002-0.124,0.071-0.105,0.141c0.082,0.304,0.162,1.08,0.202,1.945h-1.889 c-0.058,0-0.107,0.048-0.11,0.105l-0.05,0.501c-0.004,0.08,0.065,0.146,0.145,0.137l1.928,0.007c0.011,0.71-0.01,1.409-0.083,1.904 c-0.007,0.05,0.032,0.092,0.082,0.095c0.303,0.018,0.534,0.024,0.649,0.026c0.046,0.001,0.084-0.034,0.088-0.08 c0.018-0.241,0.063-0.997,0.041-1.942l1.957,0.007c0.061,0,0.11-0.049,0.11-0.111v-0.54C40.001,24.332,39.951,24.282,39.89,24.282z"></path><path fill="#fff" d="M24.488,12c-0.759,0-1.496,0.084-2.222,0.205v0c-1.925,0.321-3.706,1.044-5.266,2.088 c-1.495,1-2.792,2.274-3.79,3.772c0.349,0.076,0.687,0.17,1.018,0.272c0.766-1.085,1.71-2.03,2.772-2.825 c1.527-1.143,3.317-1.94,5.267-2.291v0C22.991,13.09,23.727,13,24.488,13c5.267,0,9.78,3.261,11.645,7.867h1.064 C35.28,15.699,30.315,12,24.488,12z"></path>
									</svg> <!-- 디즈니 아이콘  -->
									<% } %>
                                </div>
                            </div>                            
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center" ><a class="btn btn-outline-dark mt-auto" href="/Project1/view.jsp?title=<%=show.getTitle()%>">상세보기</a></div>
                            </div>
                        </div>                  
                    </div>   
    <%
	   }
   }
%>                          
                </div>                  
            </div> 
        </section>
   <!-- Bootstrap core JS-->
   <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
   <!-- Core theme JS-->
   <script src="js/scripts.js"></script>
   <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"></script>

    </body>
</html>
