const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {properties: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/properties'}).done(response => {
			this.setState({properties: response.entity._embedded.properties});
		});
	}

	render() {
		return (
			<PropertyList properties={this.state.properties}/>
		)
	}
}

class PropertyList extends React.Component{
	render() {
		const properties = this.props.properties.map(property =>
			<Property key={property._links.self.href} property={property}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>listingId</th>
						<th>realEstateType</th>
						<th>street</th>
						<th>houseNumber</th>
					</tr>
					{properties}
				</tbody>
			</table>
		)
	}
}

class Property extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.property.listingId}</td>
				<td>{this.props.property.realEstateType}</td>
				<td>{this.props.property.street}</td>
				<td>{this.props.property.houseNumber}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)