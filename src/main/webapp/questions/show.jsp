<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>远程教育考试平台_在线考试</title>
<link href="../Css/main.css" rel="stylesheet" type="text/css" />
<link href="../Css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="../Css/test.css" rel="stylesheet" type="text/css" />

<style>
.hasBeenAnswer {
	background: #5d9cec;
	color:#fff;
}

</style>
</head>

<body>
	<div class="main">
		<!--nr start-->
		<div class="test_main">
			
				<div class="test">
					<form action="" method="post">
						<div class="test_title">

							<font><input type="button" name="test_jiaojuan" value="交卷"></font>
						</div>
						
							<div class="test_content">
								<div class="test_content_title">
									<h2>${ilist[0].index_name}教师评教</h2>
									<p>
										<span>共</span><i class="content_lit">${fn:length(ilist)-1}</i><span>题</span>
									</p>
								</div>
							</div>
							
							<div class="test_content_nr">
							 <c:forEach   var = "i" begin="1" end="${fn:length(ilist)-1}">
							 <div id="/${ilist[i].index_id}">
									<div class="test_content_nr_tt">
										<i>${i}</i><span>(1分)</span><font>${ilist[i].index_name}</font><b class="icon iconfont">&#xe881;</b>
									</div>

									<div class="test_content_nr_main">
										<ul>

											<li class="option">

												<input type="radio" class="radioOrCheck" name="answer${i}"
													   id="0_answer_${i}_option_1"
														/>


												<label for="0_answer_${i}_option_1">
													A.
													<p class="ue" style="display: inline;">非常好</p>
												</label>
											</li>

											<li class="option">

												<input type="radio" class="radioOrCheck" name="answer${i}"
													   id="0_answer_${i}_option_2"
														/>


												<label for="0_answer_${i}_option_2">
													B.
													<p class="ue" style="display: inline;">好</p>
												</label>
											</li>

											<li class="option">

												<input type="radio" class="radioOrCheck" name="answer${i}"
													   id="0_answer_${i}_option_3"
														/>


												<label for="0_answer_${i}_option_3">
													C.
													<p class="ue" style="display: inline;">一般</p>
												</label>
											</li>

											<li class="option">

												<input type="radio" class="radioOrCheck" name="answer${i}"
													   id="0_answer_${i}_option_4"
														/>


												<label for="0_answer_${i}_option_4">
													D.
													<p class="ue" style="display: inline;">差</p>
												</label>
											</li>

										</ul>
										</div>
										</div>
										
							</c:forEach>
								
									
								
							</div>
						

						
					</form>
				</div>

			</div>
		</div>


	<script src="../Js/jquery.js"></script>
	<script src="../Js/jquery.easy-pie-chart.js"></script>


	<script>
		
		
		$(function() {
			$('li.option label').click(function() {
			
				var examId = $(this).closest('.test_content_nr_main').closest('li').attr('id'); // 得到题目ID
				var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
				// 设置已答题
				if(!cardLi.hasClass('hasBeenAnswer')){
					cardLi.addClass('hasBeenAnswer');
				}
				
			});
		});
	</script>


</body>

</html>