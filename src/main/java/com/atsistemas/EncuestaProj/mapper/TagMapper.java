package com.atsistemas.EncuestaProj.mapper;

import java.util.Set;

import com.atsistemas.EncuestaProj.dto.TagDTO;
import com.atsistemas.EncuestaProj.dto.TagDTOPost;
import com.atsistemas.EncuestaProj.model.Tag;

public interface TagMapper {

	public Tag tagDtoToDao(TagDTO tagDTO);
	public TagDTOPost  tagDaoToDto(Tag tag);
	public Set<Tag> tagsGetDtoToDao(Set<TagDTO> tagsDTO);
	public Set<TagDTOPost> tagsGetDaoToDto(Set<Tag> tags);
	
}
