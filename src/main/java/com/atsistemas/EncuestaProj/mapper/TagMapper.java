package com.atsistemas.EncuestaProj.mapper;

import java.util.List;

import com.atsistemas.EncuestaProj.dto.TagDTO;
import com.atsistemas.EncuestaProj.dto.TagDTOPost;
import com.atsistemas.EncuestaProj.model.Tag;

public interface TagMapper {

	public Tag tagDtoToDao(TagDTO tagDTO);
	public TagDTOPost  tagDaoToDto(Tag tag);
	public List<Tag> tagsGetDtoToDao(List<TagDTO> tagsDTO);
	public List<TagDTOPost> tagsGetDaoToDto(List<Tag> tags);
	
}
