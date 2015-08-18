<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

现在是显示的是网页中内容：
<br>

<tags:toHtml escapeHtml="false">
	<font color="red" size="3">标签文件的讲解</font>
</tags:toHtml>
<p>

	现在显示的是网页的HML代码：<br>

	<tags:toHtml escapeHtml="true">
		<font color="red" size="3"> 标签文件的讲解 </font>
	</tags:toHtml>
<p>