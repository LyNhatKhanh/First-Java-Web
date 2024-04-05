<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Danh sách bài viết</title>
</head>

<body>
<!-- when submit => go to '/admin-new' and .doGet  -->
<div class="main-content">
    <form action="<c:url value='/admin-new'/>" id="formSubmit" method="get">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang chủ</a>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box table-filter">
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                           data-toggle="tooltip"
                                           title='Thêm bài viết' href='<c:url value="/admin-new?type=edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                        </a>
                                        <button id="btnDelete" type="button"
                                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>Tên bài viết</th>
                                            <th>Mô tả ngắn</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <!-- var: variable; items: binding List<Model> -->
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <td>${item.title}</td>
                                                <td>${item.shortDescription}</td>
                                                <td>
                                                    <c:url var="editURL" value="/admin-new">
                                                        <c:param name="type" value="edit"/>
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Cập nhật bài viết" href='${editURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <ul class="pagination" id="pagination"></ul>
                                    <!-- server get data: request.getParameter("page") -->
                                        <%-- id: declare to invoke in jQuery ; name: declare to invoke in field-of-Model
                                             name resemble fieldOfModel to binding data => match each other by Map{key:value} --%>
                                    <!-- this is appear on URL 'admin-new?trang=2&maxPageItem=2' -->
                                    <input type="hidden" value="" id="type" name="type"/>
                                    <input type="hidden" value="" id="page" name="page"/>
                                    <input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
                                    <input type="hidden" value="" id="sortName" name="sortName"/>
                                    <input type="hidden" value="" id="sortBy" name="sortBy"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /.main-content -->
<script type="text/javascript">
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};            // offset
    var limit = 2;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,    // số nút page trên thanh ul
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    // val(): used to return [val()] or set the value [val('title')] of attributes for the selected elements
                    $("#maxPageItem").val(limit);   // truyền dữ liệu lên tag <input>
                    $("#page").val(page);           // truyền dữ liệu lên tag <input>
                    $("#sortName").val('title');    // truyền dữ liệu lên tag <input>
                    $("#sortBy").val('desc');       // truyền dữ liệu lên tag <input>
                    $("#type").val('list');         // truyền dữ liệu lên tag <input>
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>
</body>
</html>
