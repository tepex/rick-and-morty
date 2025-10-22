package ru.work_mate.rick_and_morty.domain.model

public interface EpisodeItem : RmItem {
    override val id: Episode.Id
    override val name: Episode.Name
    override val url: Episode.Endpoint
    override val created: Episode.Created
    public val airDate: Episode.AirDate
    public val episode: Episode.Code
    public val characters: List<Character.Endpoint>

    override fun validate() {}
}