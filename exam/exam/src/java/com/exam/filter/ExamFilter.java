package com.exam.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.core.interfaces.BaseFilter;
import com.exam.core.util.FileSystem;

public class ExamFilter extends BaseFilter {
	/**
	 * 项目名
	 */
	private final static String projectName = FileSystem.getProjectName().substring(FileSystem.getProjectName().indexOf("-") + 1);

	@Override
	public void destroy() {
	}

	@Override
	protected boolean doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute("skin", "/" + projectName + "/" + getSkin());
		return true; 
	}
}