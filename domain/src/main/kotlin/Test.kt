package ru.work_mate.rick_and_morty.domain

import ru.it_arch.k3dm.ValueObject

public interface Test : ValueObject.Data {
    public val str: String

    override fun validate() {}
}

