class GraphWithAdjacencyMatrix {
	constructor() {
		this.matrix = [];
	}

	addVertex() {
		const currentLength = this.matrix.length;
		for (let i = 0; i < currentLength; i++) {
			this.matrix[i].push(0);
		}
		this.matrix.push(new Array(currentLength + 1).fill(0));
	}

	contains(vertex) {
    return Array.isArray(this.matrix[vertex])
	}

	addEdge(from, to) {
		const currentLength = this.matrix.length;
		if (from === undefined || to === undefined) {
			console.log("invalid input");
			return;
		}
		if (from + 1 > currentLength || to + 1 > currentLength || from < 0 || to < 0) {
			console.log("out of range");
			return;
		}
    this.matrix[from][to] = 1    
	}

	hasEdge(from, to) {
    return this.matrix[from][to]>0? true:false

	}

	removeEdge(from, to) {
		const currentLength = this.matrix.length;
		if (from === undefined || to === undefined) {
			console.log("invalid input");
			return;
		}
		if (from + 1 > currentLength || to + 1 > currentLength || from < 0 || to < 0) {
      return;
		}
    this.matrix[from][to] = 0;    
	}
}
