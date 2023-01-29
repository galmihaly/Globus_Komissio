package hu.unideb.inf.globus_komissio.databases.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = PrintTemplateTypes.class,
                        parentColumns = "id",
                        childColumns = "templateTypeId",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {
                @Index(value = {"templateTypeId"})
        }
)
public class PrintTemplates {

    @NonNull
    @PrimaryKey
    private long id;

    @NonNull private long templateTypeId;
    @NonNull private String name;
    @NonNull private String templateData;
    private String comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTemplateTypeId() {
        return templateTypeId;
    }

    public void setTemplateTypeId(long templateTypeId) {
        this.templateTypeId = templateTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplateData() {
        return templateData;
    }

    public void setTemplateData(String templateData) {
        this.templateData = templateData;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "PrintTemplates{" +
                "id=" + id +
                ", templateTypeId=" + templateTypeId +
                ", name='" + name + '\'' +
                ", templateData='" + templateData + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
