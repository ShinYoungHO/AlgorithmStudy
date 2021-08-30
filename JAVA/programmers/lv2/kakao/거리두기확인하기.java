class Solution {
    int[] dx = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{1, 0, -1, 0};
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int k = 0; k < 5; k++){
            String[] place = places[k];
            boolean result = true;
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(place[i].charAt(j) == 'P'){
                        result = checkValid(i, j, place);
                        if(!result) break;
                    }
                }
                if(!result) break;
            }
            if(!result) answer[k] = 0;
            else answer[k] = 1;
        }
        
        return answer;
    }
    
    public boolean checkValid(int x, int y, String[] place){
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 5 || nx < 0 || ny >= 5 || ny < 0) continue;
            if(place[nx].charAt(ny) == 'X') continue;
            if(place[nx].charAt(ny) == 'P') return false;
            for(int j = 0; j < 4; j++){
                int nnx = nx + dx[j];
                int nny = ny + dy[j];
                if(nnx >= 5 || nnx < 0 || nny >= 5 || nny < 0) continue;
                if(nnx == x && nny == y) continue;
                if(place[nnx].charAt(nny) == 'P') return false;
            }
        }
        return true;
    }
}