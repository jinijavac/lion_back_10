package org.example.restexam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/memos")
public class MemoRestController {
    private final Map<Long, String> memos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @PostMapping
    public ResponseEntity<Long> createMemo(@RequestBody String content){
        Long id = counter.incrementAndGet();
        //원래는 서비스에 넘기겠지만..
        memos.put(id,content);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getMemo(@PathVariable("id")Long id){
        String memo = memos.get(id);
        if(memo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(memo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMemo(@PathVariable Long id, @RequestBody String content){
        if(!memos.containsKey(id)){
            return ResponseEntity.notFound().build();
        }
        memos.put(id,content);
        return ResponseEntity.ok("메모수정 성공!!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemo(@PathVariable Long id){
        String removeMemo = memos.remove(id);
        if(removeMemo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("삭제 성공");
    }
}
