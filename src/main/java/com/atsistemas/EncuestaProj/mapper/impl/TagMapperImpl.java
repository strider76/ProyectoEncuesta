package com.atsistemas.EncuestaProj.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atsistemas.EncuestaProj.dto.TagDTO;
import com.atsistemas.EncuestaProj.dto.TagDTOPost;
import com.atsistemas.EncuestaProj.mapper.TagMapper;
import com.atsistemas.EncuestaProj.model.Tag;

@Component
public class TagMapperImpl implements TagMapper {

	@Autowired
	DozerBeanMapper tagMapper;
	
	@Override
	public Tag tagDtoToDao(TagDTO tagDTO) {
		return tagMapper.map(tagDTO, Tag.class);
	}

	@Override
	public TagDTOPost tagDaoToDto(Tag tag) {
		return tagMapper.map(tag, TagDTOPost.class);
	}

	@Override
	public List<Tag> tagsGetDtoToDao(List<TagDTO> tagsDTO) {
		List<Tag> tagList = new ArrayList<>();
		for (TagDTO tagDTO : tagsDTO) 
			tagList.add(tagDtoToDao(tagDTO));
		return tagList;
	}

	@Override
	public List<TagDTOPost> tagsGetDaoToDto(List<Tag> tags) {
		List<TagDTOPost> tagList = new ArrayList<>();
		for (Tag tag : tags) 
			tagList.add(tagDaoToDto(tag));
		return tagList;
	}

}
