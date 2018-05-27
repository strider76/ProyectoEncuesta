package com.atsistemas.EncuestaProj.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atsistemas.EncuestaProj.dto.TagDTO;
import com.atsistemas.EncuestaProj.dto.TagDTOPost;
import com.atsistemas.EncuestaProj.mapper.TagMapper;
import com.atsistemas.EncuestaProj.model.Tag;

@Component
public class TapMapperImpl implements TagMapper {

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
	public Set<Tag> tagsGetDtoToDao(Set<TagDTO> tagsDTO) {
		Set<Tag> tagList = new HashSet<>();
		for (TagDTO tagDTO : tagsDTO) 
			tagList.add(tagDtoToDao(tagDTO));
		return tagList;
	}

	@Override
	public Set<TagDTOPost> tagsGetDaoToDto(Set<Tag> tags) {
		Set<TagDTOPost> tagList = new HashSet<>();
		for (Tag tag : tags) 
			tagList.add(tagDaoToDto(tag));
		return tagList;
	}

}
