	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	<nav class="navbar navbar-expand-lg navbar-dark text-white">
			<div class="container px-4 px-lg-5" >
				
					</a>
					<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4" >
						
						<li class="nav-item"><a class="nav-link active"
						
							aria-current="page" href="/Project1/index.jsp">종합영상게시판</a></li>
							<form method="post">
							<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
							<li class="nav-item dropdown">
	                            <button class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">카테고리별 영상게시판</button>
	                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	                                <li><a class="dropdown-item" href="/Project1/viewCate.jsp?category=로맨스"> 로맨스</a></li>
	                                <li><a class="dropdown-item" href="/Project1/viewCate.jsp?category=코미디"> 코미디 </a></li>
	                                <li><a class="dropdown-item" href="/Project1/viewCate.jsp?category=액션"> 액션</a></li>
	                                <li><a class="dropdown-item" href="/Project1/viewCate.jsp?category=미스터리"> 미스터리</a></li>
	                                <li><a class="dropdown-item" href="/Project1/viewCate.jsp?category=SF"> SF</a></li>
	                                <li><a class="dropdown-item" href="/Project1/viewCate.jsp?category=판타지"> 판타지</a></li>
	                                <li><a class="dropdown-item" href="/Project1/viewCate.jsp?category=예능"> 예능</a></li>
	                            </ul>
	                              </form>
	                            </li>
	                            <li class="nav-item dropdown">
	                            <button class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">플랫폼별 영상게시판</button>
	                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	                                <li><a class="dropdown-item" href="/Project1/viewCate.jsp?platform=Netflix"> 넷플릭스 </a></li>
	                                <li><a class="dropdown-item" href="/Project1/viewCate.jsp?platform=Tving"> 티빙 </a></li>
	                                <li><a class="dropdown-item" href="/Project1/viewCate.jsp?platform=Diseny+"> 디즈니+ </a></li>
	                            </ul>
	                       </li>
	                       <form method="post" action="/Project1/index.jsp">
	                       	<li>
	                       	<select name="searchField">
	                       	<option value="title">제목</option>                      	
	                       	<option value="director">제작</option>
	                       	<option value="actor">배우</option>
	                       	
	                       	</select>
	                       	<input type="text" name="searchWord"/>
	                       	<button type="submit">검색하기</button>
	                       	</li>
	                     </form>
						
						<%if(session.getAttribute("UserId")!=null){%>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/Project1/allReview.jsp">내가 쓴 리뷰 조회하기</a></li>
						<%} %>					
					</ul>
				
				</div>
		</nav>
	
	                  
	
