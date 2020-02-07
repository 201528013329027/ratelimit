
local key = KEYS[1];
local limit = tonumber(ARGV[1]);
local expire = tonumber(ARGV[2])
local hasKey = redis.call('EXISTS',KEYS[1]);

if hasKey == 1 then
    local value = tonumber(redis.call('GET',KEYS[1]));
    if value >= limit then
        return -1;
    end
end
redis.call('INCR',KEYS[1]);


local ttl = redis.call('TTL',KEYS[1]);
if ttl < 0 then
    redis.call('EXPIRE',KEYS[1],expire);
end

return 1;
