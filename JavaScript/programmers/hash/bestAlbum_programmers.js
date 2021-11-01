function solution(genres, plays) {
  const hash = {};
  const genresArr = [];
  genres.forEach((genre, idx) => {
    const checkMM = [plays[idx], idx];
    if (hash[genre]) {
      hash[genre].musics.push(checkMM);
      hash[genre].count += plays[idx];
    } else {
      genresArr.push(genre);
      hash[genre] = {
        musics: [checkMM],
        count: plays[idx],
      };
    }
  });
  return genresArr
    .sort((a, b) => hash[b].count - hash[a].count)
    .map((genre) => {
      return hash[genre].musics
        .sort((a, b) => b[0] - a[0])
        .slice(0, 2)
        .map((music) => music[1]);
    })
    .flat();
}
