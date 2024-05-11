<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<body>
    <div class="content-menu-san-pham">
        <nav aria-label="breadcrumb">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/Home.jsp">Trang Chủ</a></li>
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/san-pham.jsp">Sản Phẩm</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Data</li>
                    </ol>
                </nav>
            </nav>
        <div class="information-product">
            <div class="production">
            <c:forEach var="product" items="${product}" >
			<div class="production-img">
			    <input class="button-back-banner" type="button" id="flip" value="&lt;">
			    <div class="imagess panel">
			        <img class="emgi" src="${product.img}" alt="">
			    </div>
			    <!-- Các ảnh khác của sản phẩm -->
			    <input class="button-next-banner" type="button" id="flop" value="&gt;">
			    <div class="list-img-product">
			        <!-- Hiển thị thông tin sản phẩm -->
			        <h2>${product.productName}</h2>
			        <p>Product Type: ${product.productType}</p>
			        <p>Product Category: ${product.productCategoryPath}</p>
			        <p>Unit Price: ${product.unitPrice}</p>
			        <ul class="uu">
			            <li></li>
			            <li></li>
			            <li></li>
			        </ul>
			    </div>
			</div>
                </c:forEach>   
            </div>
            <div class="priced">
                <h3 class="name-phone">${product.productName}</h3>
				<h4 class="price">${product.unitPrice}đ</h4> <span><del>${product.oldPrice}đ</del></span>
                <div class="chose-memories">
                    <p><b>Chọn dung lượng</b></p>
                    <div class="chose-memory chose">
                        <input type="radio" class="check-radio-memory" id="chose1">
						<label for="myCheckbox">64Gb</label>
						<h6 class="price">${product.unitPrice}đ</h6>
                    </div>
                    <div class="chose-memory chose">
                        <input type="radio" class="check-radio-memory" id="chose2">
                        <label for="myCheckbox" >128Gb</label>
                        <h6 class="price">12.890.000đ</h6>
                    </div>                    
                </div>
                <div class="chose-colors">
                    <p><b>Chọn màu</b></p>
                    <div class="chose chose-color">
                        <input type="radio" class="check-radio-color">
                        <label for="myCheckbox" >Đen</label>
                        <h6 class="price">11.000.000đ</h6>
                    </div>
                    <div class="chose chose-color">
                        <input type="radio" class="check-radio-color" >
                        <label for="myCheckbox" >Trắng</label>
                        <h6 class="price">12.890.000đ</h6>
                    </div>        
                    <div class="chose chose-color">
                        <input type="radio" class="check-radio-color" >
                        <label for="myCheckbox" >Vàng gold</label>
                        <h6 class="price">12.890.000đ</h6>
                    </div> 
                    <div class="chose chose-color">
                        <input type="radio" class="check-radio-color">
                        <label for="myCheckbox" >Xanh</label>
                        <h6 class="price">12.890.000đ</h6>
                    </div>             
                </div>
                <div class="pay">
                    <input type="button" class="button-pay button-pay-now" id="button-pay-now" value="Thanh toán ngay"><br>
                    <input type="button" class="button-pay button-installment" id="button-installment" value="Trả góp"><br>
                    <input type="button" class="button-pay button-pay-later" id="button-pay-later" value="Thanh toán khi nhận hàng"><br><br>
                    <button type="button" class="button-add button-add-to-card" id="button-add-to-card" value="Thêm vào giỏ hàng">Thêm vào giỏ hàng <i class="fa fa-shopping-cart"></i></button>
                    <br>
                    <p class="gom"><i class="fa fa-check-circle"></i> Đã đi kèm theo phụ kiện</p>
                    <p class="gom"><i class="fa fa-check-circle"></i> Tặng kèm sạc nhanh 20W</p>
                    <p class="gom"><i class="fa fa-check-circle"></i> Tặng kèm cáp sạc Type-C to Lightning</p>
                    <p class="gom"><i class="fa fa-check-circle"></i> Tặng kèm tai nghe</p>
                    <p class="gom"><i class="fa fa-check-circle"></i> Tặng kèm ốp lưng chống sóc</p>
                    <p class="gom"><i class="fa fa-check-circle"></i> Đã có tem niêm phong ốc</p>
                </div>
            </div>
            <div class="guarantee">
                <p class="guarant"><b>Bảo hành 12 tháng</b></p>
                <p class="guarant"><i class="fa fa-shield"></i> Bảo hành 12 tháng</p>
                <p class="guarant"><i class="fa fa-shield"></i> Hỗ trợ 1 đổi 1 trong 30 ngày</p>
            </div>
            <div class="news">
                <p class="new-item">Tất cả iPhone chính hãng VN/A, LL/A được phân phối tại Mobile Smart đều được nhập trực tiếp từ Công ty TNHH Apple Việt Nam. Mobile Smart là nhà bán lẻ ủy quyền chính thức của Apple tại Việt Nam.</p>
            </div>
            <div class="information">
                <p class="infor-item"><b>Thông số kĩ thuật của <a href="#">iPhone 11</a></b></p>
                <img class="info-img" src="LoGo_ITPLUS__1_-removebg.png" alt="">
                <div class="info-text">
                    <p><b>Công nghệ màn hình:</b> IPS LCD</p>
                    <p><b>Tần số quét:</b> 60Hz</p>
                    <p><b>Kích thước màn hình:</b> 6.1 inch</p>
                    <p><b>Hệ điều hành: </b>IOS</p>
                    <p><b>Vi xử lý: </b>A15 Bionic</p>
                    <p><b>Sim: </b>1 nano SIM và 1 eSIM</p>
                </div>
                <input type="button" class="button-show-infor" id="button-show-infor" value="Xem chi tiết">
            </div>
        </div>
       
        <%@ include file="footer.jsp"%>