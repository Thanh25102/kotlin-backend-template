package com.khipster.template.khipstertemplate.modules.attendance.branch

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.time.Instant

@Entity
@Table(name = "face_branches")
data class Branch(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @get: NotNull
    @Column(name = "title", nullable = false)
    var title: String? = null,

    @get: NotNull
    @Column(name = "description", nullable = false, length = 1000)
    var description: String? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant? = null,

    @OneToMany(mappedBy = "branch", cascade = [CascadeType.ALL], orphanRemoval = true)
    var branchGroups: MutableList<BranchGroup> = mutableListOf()

) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Branch

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id )"
    }
}

@Entity
@Table(name = "face_branch_group")
data class BranchGroup(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @get: NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    var branch: Branch? = null,

    @get: NotNull
    @Column(name = "group_id", nullable = false)
    var groupId: Long? = null

) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as BranchGroup

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id   ,   groupId = $groupId )"
    }
}
