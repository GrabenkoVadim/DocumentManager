package employment;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * For implement this task focus on clear code, and make this solution as simple readable as possible
 * Don't worry about performance, concurrency, etc
 * You can use in Memory collection for sore data
 * <p>
 * Please, don't change class name, and signature for methods save, search, findById
 * Implementations should be in a single class
 * This class could be auto tested
 */
public class DocumentManager {
    private final Map<String, Document> storage = new HashMap<>();

    /**
     * Implementation of this method should upsert the document to your storage
     * And generate unique id if it does not exist, don't change [created] field
     *
     * @param document - document content and author data
     * @return saved document
     */
    public Document save(Document document) {
        if (document != null) {
            document.setId(validateDocumentId(document));
            storage.put(document.getId(), document);
            return document;
        }

        return null;
    }

    private String validateDocumentId(Document document) {
        String id;
        if (document.getId() == null || document.getId().isEmpty()) {
            id = randomId();
        } else {
            id = document.getId();
        }
        while (storage.containsKey(id)) {
            id = randomId();
        }
        return id;
    }

    private String randomId() {
        return String.valueOf(new Random().nextInt(1, 1000));
    }

    /**
     * Implementation this method should find documents which match with request
     *
     * @param request - search request, each field could be null
     * @return list matched documents
     */
    public List<Document> search(SearchRequest request) {
        return storage.values().stream().filter(document -> isValidStrings(request.getTitlePrefixes(), document.getTitle())
                && isValidStrings(request.getContainsContents(), document.getContent())
                && isValidStrings(request.getAuthorIds(), document.getAuthor().getId())
                && isValidDate(request.getCreatedFrom(), request.getCreatedTo(), document.getCreated())).collect(Collectors.toList());
    }

    private boolean isValidStrings(List<String> fields, String field) {
        return fields != null && field != null && fields.contains(field);
    }

    private boolean isValidDate(Instant createdFrom, Instant createdTo, Instant created) {
        if (created == null) {
            return false;
        }
        if (createdFrom != null && createdTo != null) {
            return createdFrom.isBefore(createdTo)
                    && createdFrom.isBefore(created)
                    && createdTo.isAfter(created);
        }
        if (createdFrom != null) {
            return createdFrom.isBefore(created);
        }
        if (createdTo != null) {
            return createdTo.isAfter(created);
        }
        return true;
    }

    /**
     * Implementation this method should find document by id
     *
     * @param id - document id
     * @return optional document
     */
    public Optional<Document> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Data
    @Builder
    public static class SearchRequest {
        private List<String> titlePrefixes;
        private List<String> containsContents;
        private List<String> authorIds;
        private Instant createdFrom;
        private Instant createdTo;
    }

    @Data
    @Builder
    public static class Document {
        private String id;
        private String title;
        private String content;
        private Author author;
        private Instant created;
    }

    @Data
    @Builder
    public static class Author {
        private String id;
        private String name;
    }
}