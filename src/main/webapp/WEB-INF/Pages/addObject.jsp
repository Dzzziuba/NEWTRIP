<%--
  Created by IntelliJ IDEA.
  User: romachka
  Date: 01.08.17
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add object</title>
        <%@include file="/Resources/Fragments/import.jspf"%>
    </head>
    <body>
        <form class="form-horizontal" action="/admin/objects/" method="get">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default"><--Back to list</button>
                </div>
            </div>
        </form>
        <form action="/admin/objects/add" class="form-horizontal" method="post" enctype="multipart/form-data">
        <script>
             disDate(${object_add_disabledDates})
        </script>
            <div class="form-group">
                <label for="file" class="col-sm-3 control-label">Pictures </label>
                <div class="col-sm-9">
                    <label class="btn btn-default btn-file">
                        Select file...<input type="file" name="file" multiple accept="image/*" id="file" style="display: none;">
                    </label>
                </div>


            </div>

            <div class="form-group">
                <label for="name" class="col-sm-3 control-label"></label>
                <div class="col-sm-9">
                    <div id="selectedFiles"></div>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-sm-3 control-label">Name </label>
                <div class="col-sm-9">
                    <input type="text" required class="form-control" name="name" id="name" placeholder="Name"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">Date</label>
                <div class="col-sm-9">
                    <input id="date" name="date" type="text">
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-sm-3 control-label">Description </label>
                <div class="col-sm-9">
                    <textarea  class="form-control" rows="5" cols="12" maxlength="1000" id="description" name="description">
                    </textarea>
                </div>
            </div>
            <script>
                CKEDITOR.replace( 'description' );
            </script>
            <div class="form-group">
                <label for="name" class="col-sm-3 control-label">Authors </label>
                <div class="col-sm-9">
                    <input type="text" required class="form-control" name="authors" id="authors" placeholder="authors"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" value="Add object" class="btn btn-default"/>
                </div>
            </div>

        </form>
    </body>
</html>
