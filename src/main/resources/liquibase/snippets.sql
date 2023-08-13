-- from https://rextester.com/CTGVI25902
create table YourTable (folder_id int, links jsonb);

CREATE INDEX your_table_gin_idx ON YourTable
    USING  gin (links jsonb_path_ops);

insert into YourTable values
                          (761, '[{"ids": "[82293,82292]", "index": "index_1"}, {"ids": "[82293,82292]", "index": "index_2"}]'),
                          (769, '[{"ids": "[82323,82324]", "index": "index_3"}]'),
                          (572, '[{"ids": "[80031,79674,78971]", "index": "index_4"}]'),
                          (785, '[{"ids": "[82367,82369]", "index": "index_5"}, {"ids": "[82368,82371]", "index": "index_6"}]'),
                          (768, '[{"ids": "[82292,82306]", "index": "index_7"}]');

select  distinct folder_id
from    YourTable
cross join lateral
    jsonb_array_elements(links) ids(ele)
where   (ele->>'ids')::jsonb @> '[82292]'
;


-- other links
-- https://stackoverflow.com/questions/18404055/index-for-finding-an-element-in-a-json-array
-- https://www.postgresql.org/docs/current/datatype-json.html
-- https://levelup.gitconnected.com/working-with-a-jsonb-array-of-objects-in-postgresql-d2b7e7f4db87
-- https://medium.com/@thegalang/indexing-in-postgresql-and-applying-it-to-jsonb-c99ecf50a443
-- https://www.commandprompt.com/education/how-to-query-jsonb-array-of-objects-in-postgresql/
-- https://www.freecodecamp.org/news/postgresql-and-json-use-json-data-in-postgresql/
-- https://pganalyze.com/blog/gin-index
-- https://linuxhint.com/postgres-jsonb-index/
-- https://dzone.com/articles/using-jsonb-in-postgresql-how-to-effectively-store
--
-- https://stackoverflow.com/questions/18404055/index-for-finding-an-element-in-a-json-array
