package by.andreiblinets.entity;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News extends AbstractEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "author", nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn (name = "camelcase")
    private CamelCase camelCase;

    public News() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CamelCase getCamelCase() {
        return camelCase;
    }

    public void setCamelCase(CamelCase camelCase) {
        this.camelCase = camelCase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof News)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        News news = (News) o;

        if (getTitle() != null ? !getTitle().equals(news.getTitle()) : news.getTitle() != null){
            return false;
        }
        if (getBody() != null ? !getBody().equals(news.getBody()) : news.getBody() != null){
            return false;
        }
        if (getAuthor() != null ? !getAuthor().equals(news.getAuthor()) : news.getAuthor() != null){
            return false;
        }
        return getCamelCase() != null ? getCamelCase().equals(news.getCamelCase()) : news.getCamelCase() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getCamelCase() != null ? getCamelCase().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("title='").append(title).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", camelCase=").append(camelCase);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
