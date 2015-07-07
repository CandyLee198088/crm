package org.ssm.crm520.web.controller;

import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.ssm.crm520.util.AjaxResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author 李璨
 * @date 2015-5-21
 * @note activiti-model流程编辑器
 */
@Controller
@RequestMapping("/myModel")
public class ModelController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private RepositoryService repositoryService;

	@RequestMapping("/editor")
	public ModelAndView eidtor() {
		ModelAndView modelAndView = new ModelAndView("activiti/editor");
		return modelAndView;
	}

	@RequestMapping("/")
	public String index() {
		return "definition/model";
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Model> list() {
		return repositoryService.createModelQuery().list();
	}

	/**
	 * 创建模型
	 * 
	 * @param name 模型名称
	 * @param key 模型key
	 * @param description 模型描述
	 * @return 内嵌的activiti建模器视图
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/create")
	public String create(String name, String key, String description) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace",
					"http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);

			Model modelData = repositoryService.newModel();
			// jackson tool
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			description = StringUtils.defaultString(description);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
					description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name);
			modelData.setKey(StringUtils.defaultString(key));

			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(),
					editorNode.toString().getBytes("utf-8"));
			return "redirect:/myModel/editor?id=" + modelData.getId();
		} catch (Exception e) {
			logger.error("创建模型失败：", e);
		}
		return null;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(String modelId){
		AjaxResult ar = new AjaxResult();
		try {
			repositoryService.deleteModel(modelId);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setSuccess(false);
			ar.setMessage("删除模型失败!");
		}
		return ar;
	}

	/**
	 * 部署流程
	 * @param modelId  模型id
	 * @return 
	 */
	@RequestMapping("/deploy")
	@ResponseBody
	public AjaxResult deploy(String modelId) {
		AjaxResult ar = new AjaxResult();
		try {
			Model modelData = repositoryService.getModel(modelId);
			ObjectNode modelNode = (ObjectNode) new ObjectMapper()
					.readTree(repositoryService.getModelEditorSource(modelData
							.getId()));
			System.out.println(modelNode);
			byte[] bpmnBytes = null;

			BpmnJsonConverter converter = new BpmnJsonConverter();
			BpmnModel model = converter.convertToBpmnModel(modelNode);
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);

			String processName = modelData.getName() + ".bpmn20.xml";
			repositoryService.createDeployment()
					.name(modelData.getName())
					.addString(processName, new String(bpmnBytes)).deploy();
		} catch (Exception e) {
			ar.setMessage("部署失败!");
			ar.setSuccess(false);
			logger.error("根据模型部署流程失败：modelId={}", modelId, e);
		}
		return ar;
	}

}
